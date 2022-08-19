package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logger {

    @Pointcut("execution(* *(..))")
    public void loggingPointCut(){}

    @Before("loggingPointCut()")
    public void before( JoinPoint joinPoint ){
        System.out.println("Invoked before " + joinPoint.getSignature());
    }
}
