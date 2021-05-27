package com.Insurance.hm.board.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum  BoardErrorConstants implements ErrorConstants {

    NON_MATCH_AUTHOR(500,601,"employeeId와 일치하는 직원이 존재하지 않습니다.");


    private int status;
    private int code;
    private String codeName;
    private String message;

    BoardErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
