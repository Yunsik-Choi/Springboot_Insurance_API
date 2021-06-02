package com.Insurance.hm.partner.dto;

import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import lombok.Data;

@Data
public class PartnerInfoDto {

    private Long id;
    private String name;
    private String address;
    private String contactNumber;
    private PartnerCategory category;

    public PartnerInfoDto(Partner partner) {
        this.id = partner.getId();
        this.name = partner.getName();
        this.address = partner.getAddress();
        this.contactNumber = partner.getContactNumber();
        this.category = partner.getCategory();
    }
}
