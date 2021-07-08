package com.rust.demo.controller;

import com.rust.demo.common.Result;
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
    public Result characterList(@RequestBody Object object) {
        return beetlsqlService.characterList(object);
    }

    @PostMapping("/character/page")
    public Result characterPage(@RequestBody Object object) {
        return beetlsqlService.characterPage(object);
    }

    @PostMapping("/character/get")
    public Result characterGet(@RequestBody Object object) {
        return beetlsqlService.characterGet(object);
    }

    @PostMapping("/character/update")
    public Result characterUpdate(@RequestBody Object object) {
        return beetlsqlService.characterUpdate(object);
    }

    @PostMapping("/character/delete")
    public Result characterDetele(@RequestBody Object object) {
        return beetlsqlService.characterDetele(object);
    }

    @PostMapping("/enemy/list")
    public Result enemyList(@RequestBody Object object) {
        return beetlsqlService.enemyList(object);
    }

    @PostMapping("/enemy/page")
    public Result enemyPage(@RequestBody Object object) {
        return beetlsqlService.enemyPage(object);
    }

    @PostMapping("/story/list")
    public Result storyList(@RequestBody Object object) {
        return beetlsqlService.storyList(object);
    }

    @PostMapping("/story/page")
    public Result storyPage(@RequestBody Object object) {
        return beetlsqlService.storyPage(object);
    }

    @PostMapping("/team/list")
    public Result teamList(@RequestBody Object object) {
        return beetlsqlService.teamList(object);
    }

    @PostMapping("/team/page")
    public Result teamPage(@RequestBody Object object) {
        return beetlsqlService.teamPage(object);
    }
}
