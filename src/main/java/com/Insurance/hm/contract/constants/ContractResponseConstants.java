package com.Insurance.hm.contract.constants;

import lombok.Getter;

@Getter
public enum ContractResponseConstants {

    CONTRACT_NO("Contract No."), 
    CHANGE_CONTRACT_STATUS(" 계약 상태 변경 성공"),
    FIND_ALL("Contract 전체 조회");

    private String message;

    ContractResponseConstants(String message) {
        this.message = message;
    }
}
