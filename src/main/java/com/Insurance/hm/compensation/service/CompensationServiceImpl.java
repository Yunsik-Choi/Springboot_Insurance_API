package com.Insurance.hm.compensation.service;

import com.Insurance.hm.claim.domain.ClaimRepository;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.compensation.constants.CompensationErrorConstants;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.CompensationRepository;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.compensation.dto.CompensationChangeStatusRequestDto;
import com.Insurance.hm.compensation.exception.IncorrectCompensationStatusException;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CompensationServiceImpl implements CompensationService{

    private final CompensationRepository compensationRepository;

    @Override
    public Compensation findById(Long id) {
        Compensation compensation = compensationRepository.findById(id).orElseThrow(this::getNonMatchCompensation);
        return compensation;
    }

    @Override
    public Long changeStatus(Long id, CompensationChangeStatusRequestDto requestDto) {
        Compensation compensation = compensationRepository.findById(id).orElseThrow(this::getNonMatchCompensation);
        if (requestDto.getStatus().equals(CompensationStatus.보상완료)){
            compensation.changeDateTime(LocalDateTime.now());
            compensation.changeCost(requestDto.getCost());
            compensation.getClaim().changeStatus(ClaimStatus.처리완료);
        }
        else
            throw new IncorrectCompensationStatusException(CompensationErrorConstants.INCORRECT_COMPENSATION_STATUS);
        return compensation.getId();
    }

    private NonMatchIdException getNonMatchCompensation() {
        return new NonMatchIdException(CompensationErrorConstants.NON_MATCH_COMPENSATION);
    }
}
