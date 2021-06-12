package com.Insurance.hm.claim.service;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.ClaimRepository;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.dto.ClaimAddPartnerDto;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;
import com.Insurance.hm.claim.exception.*;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.CompensationRepository;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.ContractRepository;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartnerRepository;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final CompensationRepository compensationRepository;
    private final PartnerRepository partnerRepository;
    private final ClaimPartnerRepository claimPartnerRepository;

    @Override
    public Long create(ClaimCreateRequestDto createRequestDto) {
        Contract contract = contractRepository.findById(createRequestDto.getContractId())
                .orElseThrow(() -> new NonMatchIdException(ClaimErrorResponse.NON_MATCH_CONTRACT));
        Employee employee = employeeRepository.findById(createRequestDto.getEmployeeId())
                .orElseThrow(() -> new NonMatchIdException(ClaimErrorResponse.NON_MATCH_EMPLOYEE));
        if(!contract.getStatus().equals(ContractStatus.계약중))
            throw new IncorrectContractStatusForCreateClaim(ClaimErrorResponse.INCORRECT_CONTRACT_STATUS_FOR_CREATE_CLAIM);
        Claim claim = createRequestDto.toEntity(contract,employee);
        Claim saveClaim = claimRepository.save(claim);
        return saveClaim.getId();
    }

    @Override
    public Claim findById(Long id) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        return claim;
    }


    @Override
    public Long deleteById(Long id) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        claimRepository.delete(claim);
        return id;
    }

    @Override
    public Long changeClaimStatus(Long id, ClaimChangeStatusRequestDto changeStatusRequestDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        ClaimStatus status = changeStatusRequestDto.getStatus();
        checkClaimStatus(claim, status);
        return claim.getId();
    }

    @Override
    public Claim changeClaimPartnerScore(Long id, ClaimChangePartnerScoreDto changePartnerScoreDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        if(!claim.getStatus().equals(ClaimStatus.처리완료))
            throw new IncorrectClaimProcessException(ClaimErrorResponse.INCORRECT_STATUS_TO_CHANGE_PARTNERSCORE);
        if(claim.getClaimpartnerList().size()==0)
            throw new NonClaimPartnerException(ClaimErrorResponse.NON_CLAIMPARTNER);
        if(changePartnerScoreDto.getScore()>5 || changePartnerScoreDto.getScore()<0.5)
            throw new ClaimPartnerScoreBoundExceedException(ClaimErrorResponse.BOUNDARY_EXCEED_SCORE);
        claim.changePartnerScore(changePartnerScoreDto.getScore());
        return claim;
    }

    @Override
    public Long addClaimPartner(Long id, ClaimAddPartnerDto claimAddPartnerDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        Partner partner = partnerRepository.findById(claimAddPartnerDto.getPartnerId()).orElseThrow(this::getNonMatchPartner);
        if(!claim.getStatus().equals(ClaimStatus.보상심사중))
            throw new IncorrectClaimStatusForAddPartner(ClaimErrorResponse.INCORRECT_STATUS_FOR_ADD_PARTNER);
        ClaimPartner claimPartner = new ClaimPartner(claim,partner);
        claimPartnerRepository.save(claimPartner);
        return claim.getId();
    }

    @Override
    public List<Claim> findAll() {
        List<Claim> all = claimRepository.findAll();
        return all;
    }

    private NonMatchIdException getNonMatchPartner() {
        return new NonMatchIdException(ClaimErrorResponse.NON_MATCH_PARTNER);
    }

    private NonMatchIdException getNonMatchClaim() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("claim"));
    }

    private void checkClaimStatus(Claim claim, ClaimStatus status) {
        if(claim.getStatus().equals(ClaimStatus.접수완료)){
            if(status.equals(ClaimStatus.보상심사중)) {
                Compensation compensation = Compensation.builder()
                        .cost(autoExamineCompensationConst(claim))
                        .claim(claim)
                        .contract(claim.getContract())
                        .employee(claim.getEmployee())
                        .status(CompensationStatus.보상대기)
                        .build();
                compensationRepository.save(compensation);
                claim.changeStatus(status);
            }
            else if(status.equals(ClaimStatus.보상거부))
                claim.changeStatus(status);
            else if(status.equals(ClaimStatus.처리완료))
                throw new IncorrectClaimProcessException(ClaimErrorResponse.INCORRECT_CLAIM_PROCESS);
        }
        else if(claim.getStatus().equals(ClaimStatus.보상심사중))
            throw new AlreadyInProgressException(ClaimErrorResponse.ALREADY_IN_PROGRESS);
        else
            throw new AlreadyEvaluatedException(ClaimErrorResponse.ALREADY_EVALUATED);
    }

    private Long autoExamineCompensationConst(Claim claim) {
        Contract contract = claim.getContract();
        Insurance insurance = contract.getInsurance();
        double maxPrice = 0;
        if(insurance.getCategory().equals(InsuranceCategory.여행))
            maxPrice = insurance.getBasePrice()*1000;
        else if(insurance.getCategory().equals(InsuranceCategory.자동차)||insurance.getCategory().equals(InsuranceCategory.운전자))
            maxPrice = insurance.getBasePrice()*1000*contract.getAdditionalInformation().getLevel().getCar();
        else if(insurance.getCategory().equals(InsuranceCategory.화재))
            maxPrice = insurance.getBasePrice()*1000*contract.getAdditionalInformation().getLevel().getFire();
        Double compensationCost = (claim.getDamageCost()/(100-claim.getClaimRate())) + (contract.getInsurancePremium()*10);
        if(maxPrice<compensationCost)
            compensationCost = maxPrice;
        return compensationCost.longValue();
    }

}
