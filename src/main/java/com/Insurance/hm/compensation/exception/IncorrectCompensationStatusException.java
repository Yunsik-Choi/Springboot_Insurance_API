package com.Insurance.hm.compensation.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class IncorrectCompensationStatusException extends BusinessException {
    public IncorrectCompensationStatusException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
