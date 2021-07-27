package com.rust.demo.util;

import cn.hutool.core.util.StrUtil;
import org.beetl.sql.core.query.Query;
import org.beetl.sql.core.query.interfacer.StrongValue;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * beetlsql的Query查询工具
 */
public class QueryUtil {

    /**
     * 过滤空和NULL的值，
     * 如果为空或者null则不增加查询条件
     * 返回字符串两边增加%
     *
     * @param value
     * @return
     */
    public static StrongValue likeNotEmpty(Object value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return isEmpty(value);
            }

            @Override
            public Object getValue() {
                return "%" + value + "%";
            }
        };
    }

    /**
     * 过滤空和NULL的值，
     * 如果为空或者null则不增加查询条件
     *
     * @param value
     * @return
     */
    public static StrongValue filterEmpty(Object value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return isEmpty(value);
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

    /**
     * 过滤空和NULL的值，
     * 如果为空或者null则不增加查询条件
     *
     * @param value
     * @return
     */
    public static StrongValue filterNull(Object value) {
        return new StrongValue() {
            @Override
            public boolean isEffective() {
                return value != null;
            }

            @Override
            public Object getValue() {
                return value;
            }
        };
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return false;
        }
        //校验空值
        if (value instanceof String) {
            return !"".equals(((String) value).trim());
        }

        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }
        return true;
    }

    public static void setLikeField(Object entity, Query query, List<String> fields) {
        Map<String, Object> map = getFieldAndValue(entity, fields);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.andLike(entry.getKey(), QueryUtil.likeNotEmpty(entry.getValue()));
        }
    }

    public static void setEqField(Object entity, Query query, List<String> fields) {
        Map<String, Object> map = getFieldAndValue(entity, fields);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.andEq(entry.getKey(), QueryUtil.filterEmpty(entry.getValue()));
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
            Object value = null;
            try {
                value = declaredField.get(entity);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (ObjectUtils.isEmpty(value) || "null".equals(value)) {
                continue;
            }
            map.put(StrUtil.toUnderlineCase(declaredField.getName()), value);
        }
        return map;
    }
}
