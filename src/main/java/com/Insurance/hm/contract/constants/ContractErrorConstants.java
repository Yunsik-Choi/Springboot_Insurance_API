package com.Insurance.hm.contract.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum ContractErrorConstants implements ErrorConstants{

    Non_Match_Client(500,301,"clientId와 일치하는 고객이 존재하지 않습니다."),
    Non_Match_Insurance(500,302,"insuranceId와 일치하는 보험 상품이 존재하지 않습니다."),
    Non_Match_Employee(500,303,"employeeId와 일치하는 직원이 존재하지 않습니다."),
    INCORRECT_INSURANCE_STATUS_FOR_SIGN(500,304,"상품에 계약을 맺기 위해서는 상품 상태가 '결재완료'여야 합니다."),
    BAN_TRAVEL_AREA(500,305,"해당 여행 지역은 가입이 불가능합니다."),
    OVER_CREDIT_RATING(500,306,"보험의 신용등급 한도 보다 고객의 신용 등급이 더 높습니다."),
    OVER_AGE(500,307,"고객의 나이가 보험의 타겟 나이 범위 안에 존재하지 않습니다.");

    private int status;
    private int code;
    private String codeName;
    private String message;

    ContractErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
