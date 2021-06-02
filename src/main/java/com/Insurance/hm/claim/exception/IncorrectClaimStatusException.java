package com.Insurance.hm.claim.exception;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.global.exception.BusinessException;

public class IncorrectClaimStatusException extends BusinessException {
    public IncorrectClaimStatusException(ClaimErrorResponse incorrectClaimStatus) {
        super(incorrectClaimStatus);
    }
}
