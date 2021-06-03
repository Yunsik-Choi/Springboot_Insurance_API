package com.Insurance.hm.client.constants;

import lombok.Getter;

@Getter
public enum  ClientResponseConstants {

    CLIENT_NO("Client No."),
    FIND_ALL("Client 전체 조회");

    private String message;

    ClientResponseConstants(String message) {
        this.message = message;
    }
}
