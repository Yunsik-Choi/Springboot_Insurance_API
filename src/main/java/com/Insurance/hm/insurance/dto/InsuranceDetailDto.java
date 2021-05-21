package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import com.Insurance.hm.insurance.dto.employee.InsuranceEmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class InsuranceDetailDto {

    private Long id;
    private String name;
    private String description;
    private InsuranceCategory category;
    private InsuranceTarget target;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private InsuranceEmployeeDto createEmployee;
    private InsuranceEmployeeDto managementEmployee;
    private List<Contract> contractList;


    public InsuranceDetailDto(Insurance insurance){
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
        this.category = insurance.getCategory();
        this.target = insurance.getTarget();
        this.createTime = insurance.getCreated_date();
        this.modifiedTime = insurance.getModified_date();
        this.createEmployee = new InsuranceEmployeeDto(insurance.getCreate_employee());
        this.managementEmployee = new InsuranceEmployeeDto(insurance.getManagement_employee());
        this.contractList = insurance.getContract_list();
    }

}
