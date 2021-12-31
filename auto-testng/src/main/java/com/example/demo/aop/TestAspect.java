package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/30-18:35
 */
@Aspect
@Component
public class TestAspect {

    @Pointcut("@annotation(com.example.demo.annotation.TestAnnotation)")
    public void logPointcut() {

    }


    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("---TestAspect.around---");
        return joinPoint.proceed();
    }


    /**
     * 异常通知
     */
    @AfterThrowing("logPointcut()")
    public void afterThrow() {
        System.out.println("---LogPointCut.afterThrow---");
    }
}
