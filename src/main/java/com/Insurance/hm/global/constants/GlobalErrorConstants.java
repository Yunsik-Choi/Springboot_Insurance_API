package com.Insurance.hm.global.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;

@Getter
public enum GlobalErrorConstants implements ErrorConstants {
    Non_Match_Id(500,001 ,"해당 아이디와 일치하는 {정보}가 존재하지 않습니다." );

    private int status;
    private int code;
    private String codeName;
    private String message;

    GlobalErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }


    public GlobalErrorConstants setClassNameMessage(String className){
        String messageFront = this.message.substring(0, message.indexOf("{")+1);
        String messageBack = this.message.substring(message.indexOf("}"));
        this.message = messageFront+className+messageBack;
        return this;
    }
}
