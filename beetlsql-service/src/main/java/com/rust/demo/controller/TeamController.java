package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.entity.Team;
import com.rust.demo.mapper.arknights.TeamMapper;
import com.rust.demo.util.CustomPageUtil;
import com.rust.demo.util.QueryUtil;
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
    public Object list(@RequestBody Team team) {
        Query<Team> query = teamMapper.createQuery();
        QueryUtil.setLikeField(team, query, likeFields);
        QueryUtil.setEqField(team, query, eqFields);

        return query.select();
    }

    @PostMapping("/team/page")
    public Object page(@RequestBody Map<String, Object> param) {
        Query<Team> query = teamMapper.createQuery();
        Team team = BeanUtil.mapToBean(param, Team.class, true);
        QueryUtil.setLikeField(team, query, likeFields);
        QueryUtil.setEqField(team, query, eqFields);

        return query.page(CustomPageUtil.getPageRequest(param));
    }
}
