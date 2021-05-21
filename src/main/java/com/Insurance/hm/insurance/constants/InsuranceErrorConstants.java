package com.Insurance.hm.insurance.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum InsuranceErrorConstants implements ErrorConstants {
    Non_Match_Id(500,104 ,"해당 아이디와 일치하는 정보가 존재하지 않습니다." );

    private int status;
    private int code;
    private String codeName;
    private String message;

    InsuranceErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
