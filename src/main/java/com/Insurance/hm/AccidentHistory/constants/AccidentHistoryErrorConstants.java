package com.Insurance.hm.AccidentHistory.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum AccidentHistoryErrorConstants implements ErrorConstants {

    NON_MATCH_CLIENT(500,501,"clientId와 일치하는 고객이 없습니다.");

    int status;
    int code;
    String codeName;
    String message;


    AccidentHistoryErrorConstants(int status, int code, String message) {
       this.status = status;
       this.code = code;
       this.codeName = this.toString();
       this.message = message;
    }
}
