package com.Insurance.hm.global.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum GlobalErrorConstants implements ErrorConstants {
    Non_Match_Id(500,001 ,"해당 아이디와 일치하는 정보가 존재하지 않습니다." );

    private int status;
    private int code;
    private String codeName;
    private String message;

    GlobalErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
