package com.Insurance.hm.partner.dto;

import com.Insurance.hm.claim.dto.ClaimInfoDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.dto.EmployeeInfoDto;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PartnerDetailDto {

    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private PartnerCategory category;
    private EmployeeInfoDto employee;
    private List<ClaimInfoDto> claimList;

    public PartnerDetailDto(Partner partner) {
        this.id = partner.getId();
        this.name = partner.getName();
        this.address = partner.getAddress();
        this.contactNumber = partner.getContactNumber();
        this.category = partner.getCategory();
        this.employee = new EmployeeInfoDto(partner.getEmployee());
        this.claimList = partner.getClaimpartnerList().stream()
                .map(i -> new ClaimInfoDto(i.getClaim())).collect(Collectors.toList());
    }
}
