package com.Insurance.hm.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter @Setter
public class SimpleMessageException extends RuntimeException{

    private HttpStatus status;

    @Override
    public synchronized Throwable fillInStackTrace() {
        log.error(this.toString());
        return this;
    }

    public SimpleMessageException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
