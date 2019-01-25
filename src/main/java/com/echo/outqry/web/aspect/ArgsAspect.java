package com.echo.outqry.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArgsAspect {


    @Around("execution(* com.echo.outqry.web.controller.*.*(..))")
    public Object aspect(ProceedingJoinPoint pjp)throws  Throwable{
        Object obj = pjp.proceed();
        Object[] args = pjp.getArgs();
        for (Object o:args){
            System.out.println("args="+o.toString());
        }
        return obj;

    }

}
