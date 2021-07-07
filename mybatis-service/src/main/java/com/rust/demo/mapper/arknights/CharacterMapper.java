package com.rust.demo.mapper.arknights;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rust.demo.entity.Character;
import com.rust.demo.vo.CharacterVO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CharacterMapper extends BaseMapper<Character> {

    CharacterVO selectVoById(@Param("id") String id);
}