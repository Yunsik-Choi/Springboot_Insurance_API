package com.Insurance.hm.claim.exception;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class AlreadyInProgressException extends BusinessException {
    public AlreadyInProgressException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
