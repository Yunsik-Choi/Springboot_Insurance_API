package com.Insurance.hm.AccidentHistory.constants;

import lombok.Getter;

@Getter
public enum AccidentHistoryResponseConstants {


    ACCIDENT_HISTORY_NO("Accident History NO.");


    private String message;


    AccidentHistoryResponseConstants(String message) {
        this.message = message;
    }
}
