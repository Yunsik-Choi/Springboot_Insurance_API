package com.Insurance.hm.insurance.constants;


import lombok.Getter;

@Getter
public enum InsuranceResponseConstants {

    INSURANCE_NO("Insurance NO."), CHANGE_INSURANCE_STATUS(" 보험 상태 변경 성공!");


    private String message;

    InsuranceResponseConstants(String message){
        this.message = message;
    }




}
