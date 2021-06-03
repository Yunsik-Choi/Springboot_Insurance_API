package com.Insurance.hm.contract.exception;

import com.Insurance.hm.contract.constants.ContractErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class IncorrectInsuranceStatusForSign extends BusinessException {
    public IncorrectInsuranceStatusForSign(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
