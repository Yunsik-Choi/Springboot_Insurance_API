package com.Insurance.hm.compensation.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum CompensationErrorConstants implements ErrorConstants {

    NON_MATCH_COMPENSATION(500,901,"compensationId와 일치하는 보상이 존재하지 않습니다."),
    INCORRECT_COMPENSATION_STATUS(500,902,"status에 알맞지 않은 보상 상태가 입력되었습니다."),
    INCORRECT_COMPENSATION_COST(500,903,"cost는 0보다 커야 합니다.");

    private int status;
    private int code;
    private String codeName;
    private String message;

    CompensationErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
