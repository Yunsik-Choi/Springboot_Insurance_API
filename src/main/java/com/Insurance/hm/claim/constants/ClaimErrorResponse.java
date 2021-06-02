package com.Insurance.hm.claim.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum  ClaimErrorResponse implements ErrorConstants {

    NON_MATCH_CONTRACT(500,701,"contractId와 일치하는 계약이 존재하지 않습니다."),
    NON_MATCH_EMPLOYEE(500,702,"employeeId와 일치하는 직원이 존재하지 않습니다."),
    NON_MATCH_CLAIMPARTNER(500,703,"claimPatnerId와 일치하는 claimPatnerList가 존재하지 않습니다."),
    NON_CLAIMPARTNER(500,704,"Claim과 연관된 협력업체가 존재하지 않습니다."),
    BOUNDARY_EXCEED_SCORE(400,705,"Score범위는 0.5이상 5이하입니다.");

    private int status;
    private int code;
    private String codeName;
    private String message;


    ClaimErrorResponse(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
