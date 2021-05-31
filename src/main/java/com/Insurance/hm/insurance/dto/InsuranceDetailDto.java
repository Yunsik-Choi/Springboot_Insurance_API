package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.dto.ContractInfoDto;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class InsuranceDetailDto {

    private Long id;
    private String name;
    private String description;
    private InsuranceCategory category;
    private InsuranceStatus status;
    private InsuranceTarget target;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private EmployeeInfoDto createEmployee;
    private EmployeeInfoDto managementEmployee;
    private List<ContractInfoDto> contractList;


    public InsuranceDetailDto(Insurance insurance){
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
        this.category = insurance.getCategory();
        this.status = insurance.getStatus();
        this.target = insurance.getTarget();
        this.createTime = insurance.getCreatedDate();
        this.modifiedTime = insurance.getModifiedDate();
        this.createEmployee = new EmployeeInfoDto(insurance.getCreateEmployee());
        this.managementEmployee = new EmployeeInfoDto(insurance.getManagementEmployee());
        List<ContractInfoDto> contractInfoDtoList = new ArrayList<>();
        insurance.getContractList().stream().forEach(c -> contractInfoDtoList.add(new ContractInfoDto(c)));
        this.contractList = contractInfoDtoList;
    }

}
