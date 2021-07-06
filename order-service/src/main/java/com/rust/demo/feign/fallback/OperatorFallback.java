package com.rust.demo.feign.fallback;

import com.rust.demo.common.Result;
import com.rust.demo.feign.OperatorService;
import com.rust.demo.util.ResultUtil;
import org.springframework.stereotype.Component;

@Component
public class OperatorFallback implements OperatorService {
    @Override
    public Result get(Long id) {
        return ResultUtil.error();
    }
}
