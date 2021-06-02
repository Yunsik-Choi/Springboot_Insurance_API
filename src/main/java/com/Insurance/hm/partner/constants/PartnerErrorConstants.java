package com.Insurance.hm.partner.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum PartnerErrorConstants implements ErrorConstants {

    NON_MATCH_EMPLOYEE(500,801,"employeeId와 일치하는 직원이 존재하지 않습니다"),
    NON_MATCH_PARTNER(500,802,"partnerId와 일치하는 협력업체가 존재하지 않습니다.");

    private int status;
    private int code;
    private String codeName;
    private String message;

    PartnerErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
