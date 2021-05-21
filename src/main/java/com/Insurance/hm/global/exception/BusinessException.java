package com.Insurance.hm.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorConstants errorConstants;

    public BusinessException(String message, ErrorConstants errorConstants){
        super(message);
        this.errorConstants = errorConstants;
    }

    public BusinessException(ErrorConstants errorConstants){
        super(errorConstants.getMessage());
        this.errorConstants = errorConstants;
    }
}
