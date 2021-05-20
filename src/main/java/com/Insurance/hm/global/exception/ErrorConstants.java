package com.Insurance.hm.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface ErrorConstants {

    public abstract String getMessage();

    public abstract int getStatus();

}
