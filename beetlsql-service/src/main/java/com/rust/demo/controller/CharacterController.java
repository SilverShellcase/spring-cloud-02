package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Character;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.mapper.arknights.CharacterInfoMapper;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.util.ResultUtil;
import com.rust.demo.vo.CharacterVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CharacterController {

    @Resource
    private CharacterMapper characterMapper;
    @Resource
    private CharacterInfoMapper characterInfoMapper;

    @PostMapping("character/list")
    public Result list(@RequestBody Character entity) {
        List<Character> list = characterMapper.selectList(entity);
        return ResultUtil.success(list);
    }

    @PostMapping("character/get")
    public Result get(@RequestBody Character entity) {
        Character character = characterMapper.single(entity.getId());
        CharacterInfo characterInfo = characterInfoMapper.single(entity.getId());
        CharacterVO characterVO = new CharacterVO();
        BeanUtil.copyProperties(character, characterVO);
        BeanUtil.copyProperties(characterInfo, characterVO);
        return ResultUtil.success(characterVO);
    }

    @PostMapping("character/update")
    public Result update(@RequestBody Character entity) {
        characterMapper.updateById(entity);
        return ResultUtil.success();
    }

    @PostMapping("character/delete")
    public Result delete(@RequestBody Character entity) {
        characterMapper.deleteById(entity.getId());
        return ResultUtil.success();
    }
}
