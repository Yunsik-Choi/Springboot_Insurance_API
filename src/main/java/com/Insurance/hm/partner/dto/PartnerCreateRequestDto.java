package com.Insurance.hm.partner.dto;

import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import lombok.Data;

@Data
public class PartnerCreateRequestDto {

    private String name;
    private String address;
    private String contactNumber;
    private PartnerCategory category;
    private Long employeeId;

    public Partner toEntity(Employee employee){
        Partner partner = Partner.builder()
                .name(name)
                .address(address)
                .contactNumber(contactNumber)
                .category(category)
                .employee(employee)
                .build();
        return partner;
    }

}
