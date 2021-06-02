package com.Insurance.hm.claim.service;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.ClaimRepository;
import com.Insurance.hm.claim.dto.ClaimChangePartnerScoreDto;
import com.Insurance.hm.claim.dto.ClaimChangeStatusRequestDto;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;
import com.Insurance.hm.claim.exception.ClaimPartnerScoreBoundExceedException;
import com.Insurance.hm.claim.exception.NonClaimPartnerException;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.ContractRepository;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.EmployeeRepository;
import com.Insurance.hm.global.constants.GlobalErrorConstants;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
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
        claim.changeStatus(changeStatusRequestDto.getStatus());
        return claim;
    }

    @Override
    public Claim changeClaimPartnerScore(Long id, ClaimChangePartnerScoreDto changePartnerScoreDto) {
        Claim claim = claimRepository.findById(id).orElseThrow(this::getNonMatchClaim);
        if(claim.getClaimpartnerList().size()==0)
            throw new NonClaimPartnerException(ClaimErrorResponse.NON_CLAIMPARTNER);
        if(changePartnerScoreDto.getPartnerScore()>5 || changePartnerScoreDto.getPartnerScore()<0.5)
            throw new ClaimPartnerScoreBoundExceedException(ClaimErrorResponse.BOUNDARY_EXCEED_SCORE);
        claim.changePartnerScore(changePartnerScoreDto.getPartnerScore());
        return claim;
    }

    private NonMatchIdException getNonMatchClaim() {
        return new NonMatchIdException(GlobalErrorConstants.Non_Match_Id.setClassNameMessage("claim"));
    }
}
