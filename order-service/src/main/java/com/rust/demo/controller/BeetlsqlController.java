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
}
