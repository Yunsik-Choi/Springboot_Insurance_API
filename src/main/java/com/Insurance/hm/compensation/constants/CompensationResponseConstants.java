package com.Insurance.hm.compensation.constants;

import lombok.Getter;

@Getter
public enum  CompensationResponseConstants {

    COMPENSATION_NO("Compensation NO."),
    FIND_ALL("Compensation 전체 조회");


    private String message;

    CompensationResponseConstants(String message) {
        this.message = message;
    }
}
