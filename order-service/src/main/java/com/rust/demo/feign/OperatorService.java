package com.rust.demo.feign;

import com.rust.demo.common.Result;
import com.rust.demo.feign.fallback.OperatorFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "operator-service", fallback = OperatorFallback.class)
public interface OperatorService {

    @GetMapping("/operator/get/{id}")
    Result get(@PathVariable("id") Long id);
}
