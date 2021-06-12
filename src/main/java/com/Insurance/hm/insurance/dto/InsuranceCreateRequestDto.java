package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class InsuranceCreateRequestDto {

    private String name;
    private String description;
    private String coverage;
    private String registerDocument;
    private String accidentDocument;
    private Long basePrice;
    private InsuranceCategory category;
    private InsuranceTarget target;
    private Long createEmployeeId;
    private Long managementEmployeeId;



    public Insurance toEntity(Employee createEmployee, Employee managementEmployee) {
        Insurance insurance = Insurance.builder()
                .name(name)
                .description(description)
                .coverage(coverage)
                .registerDocument(registerDocument)
                .accidentDocument(accidentDocument)
                .basePrice(basePrice)
                .category(category)
                .status(InsuranceStatus.결재대기)
                .target(target)
                .createEmployee(createEmployee)
                .managementEmployee(managementEmployee)
                .build();
        return insurance;
    }
}
