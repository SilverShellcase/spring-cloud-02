package com.rust.demo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Character;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.vo.CharacterVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CharacterService extends ServiceImpl<CharacterMapper, Character> {

    public CharacterVO selectVoById(String id) {
        Map<String, Object> map = this.baseMapper.selectVoById(id);
        return BeanUtil.mapToBean(map, CharacterVO.class, true);
    }
}
