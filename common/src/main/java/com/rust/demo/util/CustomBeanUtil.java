package com.rust.demo.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class CustomBeanUtil {
    public static String[] getNullField(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Set<String> set = new HashSet<>();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    set.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return set.toArray(new String[0]);
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullField(source));
    }

    public static void main(String[] args) {
    }
}
