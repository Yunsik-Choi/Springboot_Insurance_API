package com.Insurance.hm.global.constants;

import lombok.Getter;

@Getter
public enum  GlobalConstants {

    DELETE(" 삭제 성공"), FIND_BY_ID(" 아이디로 찾기 성공");

    private String message;

    GlobalConstants(String message) {
        this.message = message;
    }
}
