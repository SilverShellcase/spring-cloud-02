package com.rust.demo.mapper.arknights;

import com.rust.demo.entity.Character;
import org.beetl.sql.mapper.BaseMapper;

import java.util.List;

public interface CharacterMapper extends BaseMapper<Character> {
    List<Character> selectList(Character character);
}
