package com.Insurance.hm.claim.exception;

import com.Insurance.hm.claim.constants.ClaimErrorResponse;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class ClaimPartnerScoreBoundExceedException extends BusinessException {
    public ClaimPartnerScoreBoundExceedException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
