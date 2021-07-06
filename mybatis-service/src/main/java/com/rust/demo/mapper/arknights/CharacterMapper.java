package com.rust.demo.mapper.arknights;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rust.demo.entity.Character;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CharacterMapper extends BaseMapper<Character> {

    Map<String, Object> selectVoById(@Param("id") String id);
}