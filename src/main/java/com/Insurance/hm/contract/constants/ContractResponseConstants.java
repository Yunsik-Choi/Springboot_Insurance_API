package com.Insurance.hm.contract.constants;

import lombok.Getter;

@Getter
public enum ContractResponseConstants {

    CONTRACT_NO("Contract No."), CHANGE_CONTRACT_STATUS(" 계약 상태 변경 성공");

    private String message;

    ContractResponseConstants(String message) {
        this.message = message;
    }
}
