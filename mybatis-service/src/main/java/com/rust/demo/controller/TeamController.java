package com.rust.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rust.demo.entity.Team;
import com.rust.demo.service.TeamService;
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
public class TeamController {

    @Resource
    private TeamService teamService;

    @Transactional("arknightsTransactionManager")
    @PostMapping("/team/import")
    public Object importTeam(@RequestParam("file") MultipartFile file) {
        ImportUtil<Team> importUtil = new ImportUtil<>();
        importUtil.importJson(file, teamService, Team.class);
        return ResultUtil.success();
    }

    @PostMapping("/team/get")
    public Object get(@RequestBody Team entity) {
        return teamService.getById(entity.getId());
    }

    @PostMapping("/team/list")
    public Object list(@RequestBody Team entity) {
        return teamService.list(new QueryWrapper<>(entity));
    }
}
