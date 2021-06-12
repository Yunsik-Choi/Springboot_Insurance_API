package com.Insurance.hm.global.aop;

import com.Insurance.hm.global.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ControllerLogResolver {

    @AfterThrowing(pointcut = "execution(* com.Insurance.hm.*.*Controller.*(..))", throwing = "e")
    public void writeExceptionLog(JoinPoint joinPoint, Exception e) throws Throwable{
        log.error(e.toString());
        log.error("JoinPoint: {}",joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.Insurance.hm.*.*Controller.*(..))", returning = "returnValue")
    public void writeSuccessLog(JoinPoint joinPoint, Object returnValue){
        log.info("SUCCESS: {}",joinPoint.getSignature().toShortString());
        if(returnValue!=null&&returnValue.getClass().equals(ResponseDto.class)) {
            ResponseDto responseDto = (ResponseDto) returnValue;
            log.info("RESPONSE: {}",responseDto.getData().toString());
        }
    }

}
