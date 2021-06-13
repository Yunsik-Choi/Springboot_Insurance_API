package com.Insurance.hm.employee.exception;

import com.Insurance.hm.employee.constants.EmployeeErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class NoEmployeeByDepartment extends BusinessException {
    public NoEmployeeByDepartment(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
