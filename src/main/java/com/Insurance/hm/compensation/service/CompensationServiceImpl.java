package com.Insurance.hm.compensation.service;

import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.compensation.constants.CompensationErrorConstants;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.CompensationRepository;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.compensation.dto.CompensationChangeStatusRequestDto;
import com.Insurance.hm.global.exception.business.NonMatchIdException;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public Long autoExamine(Long id) {
        Compensation compensation = compensationRepository.findById(id).orElseThrow(this::getNonMatchCompensation);
        InsuranceCategory category = compensation.getContract().getInsurance().getCategory();
        return null;
    }

    @Override
    public List<Compensation> findAll() {
        List<Compensation> all = compensationRepository.findAll();
        return all;
    }

    @Override
    public Long changeStatus(Long id, CompensationChangeStatusRequestDto requestDto) {
        Compensation compensation = compensationRepository.findById(id).orElseThrow(this::getNonMatchCompensation);
        if (requestDto.getStatus().equals(CompensationStatus.보상완료)){
            compensation.changeDateTime(LocalDateTime.now());
            compensation.getClaim().changeStatus(ClaimStatus.처리완료);
            compensation.changeStatus(CompensationStatus.보상완료);
        }
        return compensation.getId();
    }

    private NonMatchIdException getNonMatchCompensation() {
        return new NonMatchIdException(CompensationErrorConstants.NON_MATCH_COMPENSATION);
    }
}
