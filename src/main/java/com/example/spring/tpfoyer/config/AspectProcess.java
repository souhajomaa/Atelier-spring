package com.example.spring.tpfoyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AspectProcess {
    @Before("execution(* com.example.spring.tpfoyer.services.*.*(..))")
    public void ligMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("** in method " + name + ":");
    }

  //log après l'execution de chaque methode du service
@After("execution(* com.example.spring.tpfoyer.services.*.*(..))")
    public void ligMethodExit(JoinPoint joinPoint) {
        String name= joinPoint.getSignature().getName();
        log.info("** exiting method "+name+":");
}
    @Around("execution(* com.example.spring.tpfoyer.services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object obj = pjp.proceed();
            return obj;
        }finally {
            long elapsedTime = System.currentTimeMillis() - start;
            String methodName=pjp.getSignature().getName();
            log.info("Method [{}] execution time:{} ms" + methodName + elapsedTime);
        }


    }
}
