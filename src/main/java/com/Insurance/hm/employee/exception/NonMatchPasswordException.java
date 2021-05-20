package com.Insurance.hm.employee.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class NonMatchPasswordException extends BusinessException {
    public NonMatchPasswordException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
