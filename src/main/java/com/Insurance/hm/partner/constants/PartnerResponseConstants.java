package com.Insurance.hm.partner.constants;

import lombok.Getter;

@Getter
public enum PartnerResponseConstants {

    PARTNER_NO("Partner NO."),
    FIND_ALL("Partner 전체 조회");

    private String message;


    PartnerResponseConstants(String message) {
        this.message = message;
    }
}
