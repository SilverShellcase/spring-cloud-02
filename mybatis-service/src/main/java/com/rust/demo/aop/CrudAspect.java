package com.rust.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class CrudAspect {

    @Pointcut("execution(public * com.rust.demo.controller..*.list(..))")
    public void listPointCut() {
    }

    @Before("listPointCut()")
    public void beforeList(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Field[] fields = args[0].getClass().getDeclaredFields();
        for (Field field : fields) {
            if ("id".equals(field.getName())) {
                field.setAccessible(true);
                try {
                    field.set(args[0], null);
                } catch (IllegalAccessException e) {
                }
                break;
            }
        }
    }
}
