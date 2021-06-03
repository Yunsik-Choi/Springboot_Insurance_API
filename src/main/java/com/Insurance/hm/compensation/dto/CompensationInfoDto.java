package com.Insurance.hm.compensation.dto;

import com.Insurance.hm.claim.dto.ClaimInfoDto;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompensationInfoDto {

    private Long id;
    private CompensationStatus status;

    public CompensationInfoDto(Compensation compensation) {
        this.id = compensation.getId();
        this.status = compensation.getStatus();
    }
}
