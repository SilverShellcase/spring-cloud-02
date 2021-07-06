package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.mapper.arknights.CharacterInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class CharacterInfoService extends ServiceImpl<CharacterInfoMapper, CharacterInfo> {
}
