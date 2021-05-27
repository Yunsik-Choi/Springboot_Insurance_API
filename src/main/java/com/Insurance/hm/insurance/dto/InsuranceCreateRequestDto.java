package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class InsuranceCreateRequestDto {

    private String name;
    private String description;
    private InsuranceCategory category;
    private InsuranceTarget target;
    private Long createEmployeeId;
    private Long managementEmployeeId;



    public Insurance toEntity(Employee createEmployee, Employee managementEmployee) {
        Insurance insurance = Insurance.builder()
                .name(name)
                .description(description)
                .category(category)
                .target(target)
                .createEmployee(createEmployee)
                .managementEmployee(managementEmployee)
                .build();
        return insurance;
    }
}
