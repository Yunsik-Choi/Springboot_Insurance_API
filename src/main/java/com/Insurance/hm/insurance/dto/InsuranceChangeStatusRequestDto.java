package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import lombok.Data;

@Data
public class InsuranceChangeStatusRequestDto {

    private InsuranceStatus status;

}
