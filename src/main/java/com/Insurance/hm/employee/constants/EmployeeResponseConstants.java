package com.Insurance.hm.employee.constants;

import lombok.Getter;

@Getter
public enum EmployeeResponseConstants {

    LOGIN("로그인 성공"),
    EMPLOYEE_NO("Employee NO."),
    FIND_ALL("Employee 전체 조회"),
    FIND_BY_DEPARTMENT("부서별로 조회");

    private String message;

    EmployeeResponseConstants(String message) {
        this.message = message;
    }
}
