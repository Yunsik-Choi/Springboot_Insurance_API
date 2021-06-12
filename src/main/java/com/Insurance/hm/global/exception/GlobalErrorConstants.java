package com.Insurance.hm.global.exception;

import lombok.Getter;

@Getter
public enum  GlobalErrorConstants implements ErrorConstants{

    HTTP_METHOD_MISMATCH(400,001,"잘못된 http 요청입니다.");

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
