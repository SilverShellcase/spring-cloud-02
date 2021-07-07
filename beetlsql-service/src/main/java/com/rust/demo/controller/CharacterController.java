package com.rust.demo.controller;

import com.rust.demo.common.Result;
import com.rust.demo.entity.Character;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CharacterController {

    @Resource
    private CharacterMapper characterMapper;

    @PostMapping("character/list")
    public Result list(@RequestBody Character character) {
        List<Character> list = characterMapper.selectList(character);
        return ResultUtil.success(list);
    }
}
