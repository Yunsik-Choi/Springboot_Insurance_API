package com.Insurance.hm.contract.dto;

import com.Insurance.hm.contract.domain.entity.ContractStatus;
import lombok.Data;

@Data
public class ContractChangeStatusRequestDto {

    private Long id;
    private ContractStatus status;

}
