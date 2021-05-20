package com.Insurance.hm.employee.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class NonExistLoginIdException extends BusinessException {
    public NonExistLoginIdException(ErrorConstants non_exists_loginId) {
        super(non_exists_loginId);
    }
}
