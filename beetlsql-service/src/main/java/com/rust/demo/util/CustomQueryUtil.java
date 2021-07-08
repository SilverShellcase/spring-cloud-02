package com.rust.demo.util;

import org.beetl.sql.core.query.Query;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomQueryUtil {

    public static void setLikeField(Object entity, Query query, List<String> fields) {
        Map<String, Object> map = getFieldAndValue(entity, fields);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.andLike(entry.getKey(), "%" + entry.getValue() + "%");
        }
    }

    public static void setEqField(Object entity, Query query, List<String> fields) {
        Map<String, Object> map = getFieldAndValue(entity, fields);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.andEq(entry.getKey(), entry.getValue());
        }
    }

    private static Map<String, Object> getFieldAndValue(Object entity, List<String> fields) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field declaredField : declaredFields) {
            if (!fields.contains(declaredField.getName())) {
                continue;
            }
            declaredField.setAccessible(true);
            String value = null;
            try {
                value = (String) declaredField.get(entity);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            map.put(declaredField.getName(), value);
        }
        return map;
    }
}
