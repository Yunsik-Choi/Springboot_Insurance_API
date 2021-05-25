package com.Insurance.hm.global.exception.business;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class NonMatchIdException extends BusinessException {

    public NonMatchIdException(String message, ErrorConstants errorConstants) {
        super(message, errorConstants);
    }

    public NonMatchIdException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
