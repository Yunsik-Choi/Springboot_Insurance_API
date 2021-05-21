package com.Insurance.hm.global.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ResponseDto<T>{
    private final int status = HttpStatus.OK.value();
    private final String result = "SUCCESS";
    private String message;
    private T data;
}
