package com.rust.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Enemy;
import com.rust.demo.service.EnemyService;
import com.rust.demo.util.ImportUtil;
import com.rust.demo.util.ResultUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class EnemyController {

    @Resource
    private EnemyService enemyService;

    @Transactional("arknightsTransactionManager")
    @PostMapping("/enemy/importJson")
    public Result importJson(@RequestParam("file") MultipartFile file) {
        ImportUtil<Enemy> importUtil = new ImportUtil<>();
        importUtil.importJson(file, enemyService, Enemy.class);
        return ResultUtil.success();
    }

    @PostMapping("/enemy/get")
    public Result get(@RequestBody Enemy entity) {
        return ResultUtil.success(enemyService.getById(entity.getId()));
    }

    @PostMapping("/enemy/list")
    public Result list(@RequestBody Enemy entity) {
        return ResultUtil.success(enemyService.list(new QueryWrapper<>(entity)));
    }
}
