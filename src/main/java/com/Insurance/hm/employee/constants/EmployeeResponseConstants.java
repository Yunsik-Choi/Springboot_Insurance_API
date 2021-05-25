package com.Insurance.hm.employee.constants;

import lombok.Getter;

@Getter
public enum EmployeeResponseConstants {

    LOGIN("로그인 성공"),EMPLOYEE_NO("Employee NO.");

    private String message;

    EmployeeResponseConstants(String message) {
        this.message = message;
    }
}