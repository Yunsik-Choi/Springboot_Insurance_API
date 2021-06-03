package com.Insurance.hm.AccidentHistory.constants;

import lombok.Getter;

@Getter
public enum AccidentHistoryResponseConstants {


    ACCIDENT_HISTORY_NO("Accident History NO."),
    FIND_ALL("AccidentHistory 전체 조회");


    private String message;


    AccidentHistoryResponseConstants(String message) {
        this.message = message;
    }
}
