package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Enemy;
import com.rust.demo.mapper.arknights.EnemyMapper;
import com.rust.demo.util.CustomPageUtil;
import com.rust.demo.util.CustomQueryUtil;
import com.rust.demo.util.ResultUtil;
import org.beetl.sql.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class EnemyController {

    private final List<String> likeFields = Arrays.asList("enemyTags", "name", "enemyRace", "description", "attackType", "ability");
    private final List<String> eqFields = Arrays.asList("enemyLevel", "endure", "attack", "defence", "resistance");

    @Resource
    private EnemyMapper enemyMapper;

    @PostMapping("/enemy/list")
    public Result list(@RequestBody Enemy enemy) {
        Query<Enemy> query = enemyMapper.createQuery();
        CustomQueryUtil.setLikeField(enemy, query, likeFields);
        CustomQueryUtil.setEqField(enemy, query, eqFields);

        return ResultUtil.success(query.select());
    }

    @PostMapping("/enemy/page")
    public Result page(@RequestBody Map<String, Object> param) {
        Query<Enemy> query = enemyMapper.createQuery();
        Enemy enemy = BeanUtil.mapToBean(param, Enemy.class, true);
        CustomQueryUtil.setLikeField(enemy, query, likeFields);
        CustomQueryUtil.setEqField(enemy, query, eqFields);

        return ResultUtil.success(query.page(CustomPageUtil.getPageRequest(param)));
    }
}
