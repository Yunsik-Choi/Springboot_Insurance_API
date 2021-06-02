package com.Insurance.hm.claim.exception;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.global.exception.BusinessException;

public class IncorrectClaimProcessException extends BusinessException {
    public IncorrectClaimProcessException(ClaimErrorResponse incorrectClaimProcess) {
        super(incorrectClaimProcess);
    }
}
