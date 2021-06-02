package com.Insurance.hm.claim.service;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.ClaimRepository;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.dto.ClaimAddPartnerDto;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;
import com.Insurance.hm.claim.exception.ClaimPartnerScoreBoundExceedException;
import com.Insurance.hm.claim.exception.IncorrectClaimProcessException;
import com.Insurance.hm.claim.exception.IncorrectClaimStatusException;
import com.Insurance.hm.claim.exception.NonClaimPartnerException;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.CompensationRepository;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.ContractRepository;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartnerRepository;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimServiceImpl implements ClaimService{

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
    public Claim changeClaimStatus(Long id, ClaimChangeStatusRequestDto changeStatusRequestDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        ClaimStatus status = changeStatusRequestDto.getStatus();
        checkClaimStatusForPartner(claim, status);
        return claim;
    }

    @Override
    public Claim changeClaimPartnerScore(Long id, ClaimChangePartnerScoreDto changePartnerScoreDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        if(!claim.getStatus().equals(ClaimStatus.처리완료))
            throw new IncorrectClaimProcessException(ClaimErrorResponse.INCORRECT_STATUS_TO_CHANGE_PARTNERSCORE);
        if(claim.getClaimpartnerList().size()==0)
            throw new NonClaimPartnerException(ClaimErrorResponse.NON_CLAIMPARTNER);
        if(changePartnerScoreDto.getPartnerScore()>5 || changePartnerScoreDto.getPartnerScore()<0.5)
            throw new ClaimPartnerScoreBoundExceedException(ClaimErrorResponse.BOUNDARY_EXCEED_SCORE);
        claim.changePartnerScore(changePartnerScoreDto.getPartnerScore());
        return claim;
    }

    @Override
    public Long addClaimPartner(Long id, ClaimAddPartnerDto claimAddPartnerDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        Partner partner = partnerRepository.findById(claimAddPartnerDto.getPartnerId()).orElseThrow(this::getNonMatchPartner);
        if(claim.getStatus().equals(ClaimStatus.보상심사중)){
            ClaimPartner claimPartner = ClaimPartner.builder().build();
            claimPartner.changeClaim(claim);
            claimPartner.changePartner(partner);
            claimPartnerRepository.save(claimPartner);
        }
        else
            throw new IncorrectClaimStatusException(ClaimErrorResponse.INCORRECT_STATUS_TO_CHANGE_CLAIMPARTNER);
        return claim.getId();
    }

    private NonMatchIdException getNonMatchPartner() {
        return new NonMatchIdException(ClaimErrorResponse.NON_MATCH_PARTNER);
    }

    private NonMatchIdException getNonMatchClaim() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("claim"));
    }

    private void checkClaimStatusForPartner(Claim claim, ClaimStatus status) {
        if(status.equals(ClaimStatus.보상심사중)) {
            Compensation compensation = Compensation.builder()
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
        else
            throw new IncorrectClaimStatusException(ClaimErrorResponse.INCORRECT_CLAIM_STATUS);
    }
}
