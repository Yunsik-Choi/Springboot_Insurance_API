package com.Insurance.hm.compensation.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class IncorrectCostException extends BusinessException {
    public IncorrectCostException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
