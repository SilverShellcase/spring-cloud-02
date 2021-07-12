package com.rust.demo.controller;

import com.rust.demo.common.Result;
import com.rust.demo.feign.MybatisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class MybatisController {

    @Resource
    private MybatisService mybatisService;

    @PostMapping("/character/import")
    public Object importCharacter(@RequestParam("file") MultipartFile file) {
        return mybatisService.importCharacter(file);
    }

    @PostMapping("/character/importCharacterInfo")
    public Object importCharacterInfo(@RequestParam("file") MultipartFile file) {
        return mybatisService.importCharacterInfo(file);
    }

    @PostMapping("/enemy/import")
    public Object importEnemy(@RequestParam("file") MultipartFile file) {
        return mybatisService.importEnemy(file);
    }

    @PostMapping("/team/import")
    public Object importTeam(@RequestParam("file") MultipartFile file) {
        return mybatisService.importTeam(file);
    }

}
