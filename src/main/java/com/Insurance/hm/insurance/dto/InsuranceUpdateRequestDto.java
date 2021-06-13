package com.Insurance.hm.insurance.dto;

import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import lombok.Data;

@Data
public class InsuranceUpdateRequestDto {

    private String name;
    private String description;
    private String coverage;
    private String registerDocument;
    private String accidentDocument;
    private Long basePrice;
    private InsuranceCategory category;
    private InsuranceTarget target;
    private Long managementEmployeeId;

}
