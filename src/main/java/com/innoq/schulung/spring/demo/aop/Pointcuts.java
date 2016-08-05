package com.innoq.schulung.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Pointcuts {

    @Pointcut("execution(* com.innoq.schulung..business.*.*(..))")
    public void businessCall() {}
}
