package com.Insurance.hm.compensation.service;

import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.dto.CompensationChangeStatusRequestDto;

import java.util.List;

public interface CompensationService {

    Long changeStatus(Long id, CompensationChangeStatusRequestDto requestDto);

    Compensation findById(Long id);

    Long autoExamine(Long id);

    List<Compensation> findAll();
}
