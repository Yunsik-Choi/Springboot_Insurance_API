package com.Insurance.hm.claim.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class NonClaimPartnerException extends BusinessException {
    public NonClaimPartnerException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
