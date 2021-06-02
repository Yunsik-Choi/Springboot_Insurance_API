package com.Insurance.hm.compensation.constants;

import lombok.Getter;

@Getter
public enum  CompensationResponseConstants {

    COMPENSATION_NO("Compensation NO.");


    private String message;

    CompensationResponseConstants(String message) {
        this.message = message;
    }
}
