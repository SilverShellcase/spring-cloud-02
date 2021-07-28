package com.rust.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 通俗来讲，这里的applicationContext就是spring为我们保留下来的工厂
     */
    private static ApplicationContext context;

    /**
     * 实现了ApplicationContextAware接口后，Spring容器会自动把上下文环境对象
     * 调用ApplicationContextAware接口中的setApplicationContext方法进行设置
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.context == null) {
            SpringContextUtil.context = applicationContext;
        }
    }

    /**
     * 通过在工厂中获取对象的方法
     * public static Object getBean(String beanName){
     * return applicationContext.getBean(beanName);
     * }
     * 将上面的方法使用泛型进行优化;因为上面那个方法需要强制类型转换
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
