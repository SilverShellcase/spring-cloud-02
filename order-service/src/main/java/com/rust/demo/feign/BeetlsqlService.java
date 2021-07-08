package com.rust.demo.feign;

import com.rust.demo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "beetlsql-service")
public interface BeetlsqlService {

    @PostMapping("/character/list")
    Result characterList(Object object);

    @PostMapping("/character/page")
    Result characterPage(Object object);

    @PostMapping("/character/get")
    Result characterGet(Object object);

    @PostMapping("/character/update")
    Result characterUpdate(Object object);

    @PostMapping("/character/delete")
    Result characterDetele(Object object);
}
