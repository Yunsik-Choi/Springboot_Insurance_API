package com.Insurance.hm.contract.exception;

import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class BanTravelAreaException extends BusinessException {
    public BanTravelAreaException(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
