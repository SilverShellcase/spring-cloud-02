package com.rust.demo.util;

import com.rust.demo.common.Constant;
import org.beetl.sql.core.page.DefaultPageRequest;
import org.beetl.sql.core.page.PageRequest;

import java.util.Map;

public class PageUtil {

    public static PageRequest getPageRequest(Map<String, Object> param) {
        return (PageRequest) param.getOrDefault(Constant.PAGE_REQUEST, DefaultPageRequest.of(1L, 10));
    }
}
