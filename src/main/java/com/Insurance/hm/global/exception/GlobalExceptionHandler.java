package com.Insurance.hm.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e){
        ErrorConstants errorConstants = e.getErrorConstants();
        ErrorResponse response = new ErrorResponse(e, errorConstants, errorConstants.getStatus());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorConstants.getStatus()));
    }

}
