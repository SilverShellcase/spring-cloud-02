package com.rust.demo.controller;

import com.rust.demo.feign.BeetlsqlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BeetlsqlController {

    @Resource
    private BeetlsqlService beetlsqlService;

    @PostMapping("/character/list")
    public Object characterList(@RequestBody Object object) {
        return beetlsqlService.characterList(object);
    }

    @PostMapping("/character/page")
    public Object characterPage(@RequestBody Object object) {
        return beetlsqlService.characterPage(object);
    }

    @PostMapping("/character/get")
    public Object characterGet(@RequestBody Object object) {
        return beetlsqlService.characterGet(object);
    }

    @PostMapping("/character/update")
    public Object characterUpdate(@RequestBody Object object) {
        return beetlsqlService.characterUpdate(object);
    }

    @PostMapping("/character/delete")
    public Object characterDetele(@RequestBody Object object) {
        return beetlsqlService.characterDetele(object);
    }

    @PostMapping("/enemy/list")
    public Object enemyList(@RequestBody Object object) {
        return beetlsqlService.enemyList(object);
    }

    @PostMapping("/enemy/page")
    public Object enemyPage(@RequestBody Object object) {
        return beetlsqlService.enemyPage(object);
    }

    @PostMapping("/story/list")
    public Object storyList(@RequestBody Object object) {
        return beetlsqlService.storyList(object);
    }

    @PostMapping("/story/page")
    public Object storyPage(@RequestBody Object object) {
        return beetlsqlService.storyPage(object);
    }

    @PostMapping("/team/list")
    public Object teamList(@RequestBody Object object) {
        return beetlsqlService.teamList(object);
    }

    @PostMapping("/team/page")
    public Object teamPage(@RequestBody Object object) {
        return beetlsqlService.teamPage(object);
    }
}
