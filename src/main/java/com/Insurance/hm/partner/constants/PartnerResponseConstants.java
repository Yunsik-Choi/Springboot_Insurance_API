package com.Insurance.hm.partner.constants;

import lombok.Getter;

@Getter
public enum PartnerResponseConstants {

    PARTNER_NO("Partner NO.");

    private String message;


    PartnerResponseConstants(String message) {
        this.message = message;
    }
}
