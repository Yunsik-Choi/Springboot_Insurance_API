package com.Insurance.hm.client.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;

@Getter
public enum ClientErrorConstants implements ErrorConstants {

    Non(500,401,"message");

    private int status;
    private int code;
    private String codeName;
    private String message;


    ClientErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
