package com.Insurance.hm.global.util;

import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.sql.Clob;

@Component
public class ClobHandler {

    public String toString(Clob clob){
        StringBuffer stringBuffer = new StringBuffer();
        String result = stringBuffer.append(clob).toString();
        return result;
    }

    public Clob stringToClob(String str){

        return null;
    }


}
