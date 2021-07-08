package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Character;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.entity.Story;
import com.rust.demo.mapper.arknights.CharacterInfoMapper;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.mapper.arknights.StoryMapper;
import com.rust.demo.util.CustomBeanUtil;
import com.rust.demo.util.PageUtil;
import com.rust.demo.util.ResultUtil;
import com.rust.demo.vo.CharacterVO;
import org.beetl.sql.core.page.PageResult;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class CharacterController {

    @Resource
    private CharacterMapper characterMapper;
    @Resource
    private CharacterInfoMapper characterInfoMapper;
    @Resource
    private StoryMapper storyMapper;

    @PostMapping("character/list")
    public Result list(@RequestBody Character entity) {
        return ResultUtil.success(characterMapper.selectList(entity));
    }

    @PostMapping("character/page")
    public Result page(@RequestBody Map<String, Object> param) {
        LambdaQuery<Character> lambdaQuery = characterMapper.createLambdaQuery();
        Character character = BeanUtil.mapToBean(param, Character.class, true);
        if (!StringUtils.isEmpty(character.getProfession())) {
            lambdaQuery.andLike(Character::getProfession, "%" + character.getProfession() + "%");
        }
        PageResult<Character> page = lambdaQuery.page(PageUtil.getPageRequest(param));
        return ResultUtil.success(page);
    }

    @PostMapping("character/get")
    public Result get(@RequestBody Character entity) {
        Character character = characterMapper.single(entity.getId());
        CharacterInfo characterInfo = characterInfoMapper.single(entity.getId());
        LambdaQuery<Story> lambdaQuery = storyMapper.createLambdaQuery();
        CharacterVO characterVO = new CharacterVO();
        CustomBeanUtil.copyProperties(character, characterVO);
        CustomBeanUtil.copyProperties(characterInfo, characterVO);
        List<Story> storyList = lambdaQuery.andEq(Story::getCharId, characterVO.getId()).select();
        characterVO.setStoryList(storyList);
        return ResultUtil.success(characterVO);
    }

    @Transactional("arknightsTransactionManager")
    @PostMapping("character/update")
    public Result update(@RequestBody CharacterVO entity) {
        Character character = new Character();
        CustomBeanUtil.copyProperties(entity, character);
        characterMapper.updateById(character);
        CharacterInfo characterInfo = new CharacterInfo();
        CustomBeanUtil.copyProperties(entity, characterInfo);
        characterInfoMapper.updateById(characterInfo);
        return ResultUtil.success();
    }

    @Transactional("arknightsTransactionManager")
    @PostMapping("character/delete")
    public Result delete(@RequestBody Character entity) {
        characterMapper.deleteById(entity.getId());
        characterInfoMapper.deleteById(entity.getId());
        return ResultUtil.success();
    }
}
