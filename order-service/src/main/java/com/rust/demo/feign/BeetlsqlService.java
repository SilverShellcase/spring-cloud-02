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

    @PostMapping("/enemy/list")
    Result enemyList(Object object);

    @PostMapping("/enemy/page")
    Result enemyPage(Object object);

    @PostMapping("/story/list")
    Result storyList(Object object);

    @PostMapping("/story/page")
    Result storyPage(Object object);

    @PostMapping("/team/list")
    Result teamList(Object object);

    @PostMapping("/team/page")
    Result teamPage(Object object);
}
