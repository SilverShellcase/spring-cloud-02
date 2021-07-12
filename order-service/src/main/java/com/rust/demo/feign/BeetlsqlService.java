package com.rust.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "beetlsql-service")
public interface BeetlsqlService {

    @PostMapping("/character/list")
    Object characterList(Object object);

    @PostMapping("/character/page")
    Object characterPage(Object object);

    @PostMapping("/character/get")
    Object characterGet(Object object);

    @PostMapping("/character/update")
    Object characterUpdate(Object object);

    @PostMapping("/character/delete")
    Object characterDetele(Object object);

    @PostMapping("/enemy/list")
    Object enemyList(Object object);

    @PostMapping("/enemy/page")
    Object enemyPage(Object object);

    @PostMapping("/story/list")
    Object storyList(Object object);

    @PostMapping("/story/page")
    Object storyPage(Object object);

    @PostMapping("/team/list")
    Object teamList(Object object);

    @PostMapping("/team/page")
    Object teamPage(Object object);
}
