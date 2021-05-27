package com.Insurance.hm.board.constants;

import lombok.Getter;

@Getter
public enum  BoardResponseConstants {


    BOARD_NO("Board NO.");

    private String message;

    BoardResponseConstants(String message) {
        this.message = message;
    }
}
