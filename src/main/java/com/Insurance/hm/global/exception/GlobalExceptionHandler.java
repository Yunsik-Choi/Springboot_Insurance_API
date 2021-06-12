package com.Insurance.hm.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e){
        ErrorConstants errorConstants = e.getErrorConstants();
        ErrorResponse response = new ErrorResponse(e, errorConstants, errorConstants.getStatus());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorConstants.getStatus()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodMismatchException(MethodArgumentTypeMismatchException e){
        ErrorConstants errorConstants = GlobalErrorConstants.HTTP_METHOD_MISMATCH;
        ErrorResponse response = new ErrorResponse(e,errorConstants);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorConstants.getStatus()));
    }

}
