package com.rust.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Team;
import com.rust.demo.service.TeamService;
import com.rust.demo.util.ImportUtil;
import com.rust.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/team/importJson")
    public Result importJson(@RequestParam("file") MultipartFile file) {
        ImportUtil<Team> importUtil = new ImportUtil<>();
        importUtil.importJson(file, teamService, Team.class);
        return ResultUtil.success();
    }

    @PostMapping("/team/get")
    public Result get(@RequestBody Team team) {
        return ResultUtil.success(teamService.getById(team.getId()));
    }

    @PostMapping("/team/list")
    public Result list(@RequestBody Team team) {
        return ResultUtil.success(teamService.list(new QueryWrapper<>(team)));
    }
}
