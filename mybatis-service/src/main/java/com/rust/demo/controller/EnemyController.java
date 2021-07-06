package com.rust.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Enemy;
import com.rust.demo.service.EnemyService;
import com.rust.demo.util.ImportUtil;
import com.rust.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EnemyController {

    @Autowired
    private EnemyService enemyService;

    @PostMapping("/enemy/importJson")
    public Result importJson(@RequestParam("file") MultipartFile file) {
        ImportUtil<Enemy> importUtil = new ImportUtil<>();
        importUtil.importJson(file, enemyService, Enemy.class);
        return ResultUtil.success();
    }

    @PostMapping("/enemy/get")
    public Result get(@RequestBody Enemy enemy) {
        return ResultUtil.success(enemyService.getById(enemy.getId()));
    }

    @PostMapping("/enemy/list")
    public Result list(@RequestBody Enemy enemy) {
        return ResultUtil.success(enemyService.list(new QueryWrapper<>(enemy)));
    }
}
