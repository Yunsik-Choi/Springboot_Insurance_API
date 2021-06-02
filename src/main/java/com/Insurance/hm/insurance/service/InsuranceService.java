package com.Insurance.hm.insurance.service;

import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.dto.InsuranceChangeStatusRequestDto;
import com.Insurance.hm.insurance.dto.InsuranceCreateRequestDto;

public interface InsuranceService {

    Long create(InsuranceCreateRequestDto insurance);

    Insurance findById(Long id);

    Long deleteById(Long id);

    Insurance changeStatus(Long id, InsuranceChangeStatusRequestDto changeStatusRequestDto);

}
