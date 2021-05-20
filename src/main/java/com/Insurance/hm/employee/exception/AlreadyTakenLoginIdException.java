package com.Insurance.hm.employee.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class AlreadyTakenLoginIdException extends BusinessException {
    public AlreadyTakenLoginIdException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
