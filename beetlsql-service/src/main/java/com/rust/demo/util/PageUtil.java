package com.rust.demo.util;

import com.rust.demo.common.Constant;
import org.beetl.sql.core.page.DefaultPageRequest;
import org.beetl.sql.core.page.PageRequest;

import java.util.Map;

public class PageUtil {

    public static PageRequest getPageRequest(Map<String, Object> param) {
        long page;
        int pageSize;
        try {
            page = Long.parseLong((String) param.get(Constant.PAGE));
            pageSize = Integer.parseInt((String) param.get(Constant.PAGE_SIZE));
        } catch (Exception ex) {
            page = 1L;
            pageSize = 10;
        }
        return DefaultPageRequest.of(page, pageSize);
    }
}
