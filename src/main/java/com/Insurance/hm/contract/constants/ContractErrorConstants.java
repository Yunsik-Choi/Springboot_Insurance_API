package com.Insurance.hm.contract.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum ContractErrorConstants implements ErrorConstants{

    Non_Match_Client(500,301,"clientId와 일치하는 고객이 존재하지 않습니다."),
    Non_Match_Insurance(500,302,"insuranceId와 일치하는 보험 상품이 존재하지 않습니다."),
    Non_Match_Employee(500,303,"employeeId와 일치하는 직원이 존재하지 않습니다.");

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
