package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Enemy;
import com.rust.demo.mapper.arknights.EnemyMapper;
import org.springframework.stereotype.Service;

@Service
public class EnemyService extends ServiceImpl<EnemyMapper, Enemy> {
}
