package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Character;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.vo.CharacterVO;
import org.springframework.stereotype.Service;

@Service
public class CharacterService extends ServiceImpl<CharacterMapper, Character> {

    public CharacterVO selectVoById(String id) {
        return this.baseMapper.selectVoById(id);
    }
}
