package com.innoq.schulung.spring.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAdvice {
    private final Logger log = LoggerFactory.getLogger(PerformanceLoggingAdvice.class);

    @Around("com.innoq.schulung.spring.demo.aop.Pointcuts.businessCall()")
    public Object logBusinessCall(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("Call of " + pjp.toShortString() + " took " + (end - start) + " ms");
        return result;
    }
}
