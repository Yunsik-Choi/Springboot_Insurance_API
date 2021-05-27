package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InsuranceInfoDto {

    private Long id;
    private String name;
    private String description;
    private InsuranceCategory category;

    public InsuranceInfoDto(Insurance insurance) {
        this.id = insurance.getId();
        this.name = insurance.getName();
        this.description = insurance.getDescription();
        this.category = insurance.getCategory();
    }
}
