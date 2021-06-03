package com.Insurance.hm.claim.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum  ClaimErrorResponse implements ErrorConstants {

    NON_MATCH_CONTRACT(500,701,"contractId와 일치하는 계약이 존재하지 않습니다."),
    NON_MATCH_EMPLOYEE(500,702,"employeeId와 일치하는 직원이 존재하지 않습니다."),
    NON_MATCH_PARTNER(500,703,"patnerId와 일치하는 patner가 존재하지 않습니다."),
    NON_CLAIMPARTNER(500,704,"Claim과 연관된 협력업체가 존재하지 않습니다."),
    BOUNDARY_EXCEED_SCORE(400,705,"Score범위는 0.5이상 5이하입니다."),
    INCORRECT_CLAIM_PROCESS(500,706,"아직 보상평가가 이루어지지 않았습니다."),
    INCORRECT_CONTRACT_STATUS_FOR_CREATE_CLAIM(500,707,"사고를 접수하기 위해서는 계약상태가 '계약중'이여야 합니다."),
    INCORRECT_STATUS_FOR_ADD_PARTNER(400,708,"partner를 추가하기 위해선 사고 상태가 보상심사중이여야합니다."),
    INCORRECT_STATUS_TO_CHANGE_PARTNERSCORE(400,709,"partnerScore를 바꾸기 위해선 사고 상태가 처리완료여야합니다."),
    ALREADY_IN_PROGRESS(500,710,"이미 보상 심사 중인 사고입니다."),
    ALREADY_EVALUATED(500,711,"이미 처리된 사고입니다.");

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
