package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Team;
import com.rust.demo.mapper.arknights.TeamMapper;
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
public class TeamController {

    private final List<String> likeFields = Arrays.asList("powerName", "powerCode");
    private final List<String> eqFields = Arrays.asList("powerLevel", "isLimited", "isRaw");

    @Resource
    private TeamMapper teamMapper;

    @PostMapping("/team/list")
    public Result list(@RequestBody Team team) {
        Query<Team> query = teamMapper.createQuery();
        CustomQueryUtil.setLikeField(team, query, likeFields);
        CustomQueryUtil.setEqField(team, query, eqFields);

        return ResultUtil.success(query.select());
    }

    @PostMapping("/team/page")
    public Result page(@RequestBody Map<String, Object> param) {
        Query<Team> query = teamMapper.createQuery();
        Team team = BeanUtil.mapToBean(param, Team.class, true);
        CustomQueryUtil.setLikeField(team, query, likeFields);
        CustomQueryUtil.setEqField(team, query, eqFields);

        return ResultUtil.success(query.page(CustomPageUtil.getPageRequest(param)));
    }
}
