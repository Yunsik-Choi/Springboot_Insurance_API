package com.Insurance.hm.employee.constants;

import com.Insurance.hm.global.exception.ErrorConstants;
import lombok.Getter;


@Getter
public enum EmployeeErrorConstants implements ErrorConstants {

    Already_Exists_LoginId(500,101,"이미 존재하는 아이디입니다."),
    Non_Exists_loginId(500,102,"존재하지 않는 아이디입니다."),
    Non_Match_Password(500,103,"비밀번호가 일치하지 않습니다."),
    Non_Match_Id(500,104 ,"해당 아이디와 일치하는 Employee가 존재하지 않습니다." );

    private int status;
    private int code;
    private String codeName;
    private String message;

    EmployeeErrorConstants(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.codeName = this.toString();
        this.message = message;
    }
}
