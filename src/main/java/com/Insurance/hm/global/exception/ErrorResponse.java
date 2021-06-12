package com.Insurance.hm.global.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int status;
    private final String result = "FAIL";
    private String trace;
    private ErrorConstants code;

    public ErrorResponse(Exception e, ErrorConstants code, int status) {
        this.status = status;
        this.trace = e.getClass().toString();
        this.code = code;
    }

    public ErrorResponse(Exception e, ErrorConstants code) {
        this.status = code.getStatus();
        this.code = code;
        this.trace = e.getClass().toString();
    }

}
