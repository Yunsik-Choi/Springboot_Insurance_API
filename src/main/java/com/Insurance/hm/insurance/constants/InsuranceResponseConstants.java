package com.Insurance.hm.insurance.constants;


import lombok.Getter;

@Getter
public enum InsuranceResponseConstants {

    INSURANCE_NO("Insurance NO.");


    private String message;

    InsuranceResponseConstants(String message){
        this.message = message;
    }




}
