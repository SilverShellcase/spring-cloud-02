package com.rust.demo.aop;

import com.rust.demo.common.Constant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.beetl.sql.core.page.DefaultPageRequest;
import org.beetl.sql.core.page.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Aspect
@Component
public class CrudAspect {

    @Pointcut("execution( * com.rust.demo.controller..*.page(..))")
    public void pagePointcut() {
    }

    @Before("pagePointcut()")
    public void pageBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Map) {
                Map<String, String> map = (Map<String, String>) arg;
                ((Map<?, ?>) arg).remove(Constant.PAGE_REQUEST);
                if (StringUtils.isEmpty(map.get(Constant.PAGE)) || StringUtils.isEmpty(map.get(Constant.PAGE_SIZE))) {
                    return;
                }
                PageRequest pageRequest = DefaultPageRequest.of(Long.parseLong(map.get(Constant.PAGE)), Integer.parseInt(map.get(Constant.PAGE_SIZE)));
                ((Map<String, Object>) arg).put(Constant.PAGE_REQUEST, pageRequest);
                break;
            }
        }
    }
}
