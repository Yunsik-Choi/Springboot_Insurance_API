package com.Insurance.hm.board.constants;

import lombok.Getter;

@Getter
public enum  BoardResponseConstants {


    BOARD_NO("Board NO."),
    FIND_ALL("Board 전체 조회");

    private String message;

    BoardResponseConstants(String message) {
        this.message = message;
    }
}
