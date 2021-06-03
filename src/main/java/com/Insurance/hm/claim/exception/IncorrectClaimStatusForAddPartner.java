package com.Insurance.hm.claim.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class IncorrectClaimStatusForAddPartner extends BusinessException {
    public IncorrectClaimStatusForAddPartner(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
