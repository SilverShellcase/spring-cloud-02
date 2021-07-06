package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Team;
import com.rust.demo.mapper.arknights.TeamMapper;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends ServiceImpl<TeamMapper, Team> {
}
