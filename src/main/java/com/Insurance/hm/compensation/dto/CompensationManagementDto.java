package com.Insurance.hm.compensation.dto;

import com.Insurance.hm.claim.dto.ClaimInfoDto;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.insurance.dto.InsuranceInfoDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompensationManagementDto {
    private Long id;
    private Long cost;
    private LocalDateTime dateTime;
    private CompensationStatus status;
    private ClaimInfoDto claim;
    private ContractInfoDto contract;
    private EmployeeInfoDto employee;


    public CompensationManagementDto(Compensation compensation) {
        id = compensation.getId();
        cost = compensation.getCost();
        dateTime = compensation.getDateTime();
        status = compensation.getStatus();
        claim = new ClaimInfoDto(compensation.getClaim());
        contract = new ContractInfoDto(compensation.getContract());
        employee = new EmployeeInfoDto(compensation.getEmployee());
    }
}
