package com.Insurance.hm.client.constants;

import lombok.Getter;

@Getter
public enum  ClientResponseConstants {

    CLIENT_NO("Client No.");

    private String message;

    ClientResponseConstants(String message) {
        this.message = message;
    }
}
