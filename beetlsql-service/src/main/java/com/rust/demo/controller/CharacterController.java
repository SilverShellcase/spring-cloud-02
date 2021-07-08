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
import com.rust.demo.util.CustomPageUtil;
import com.rust.demo.util.CustomQueryUtil;
import com.rust.demo.util.ResultUtil;
import com.rust.demo.vo.CharacterVO;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class CharacterController {

    private final List<String> likeFields = Arrays.asList("name", "description", "appellation", "tagList", "itemObtainApproach");
    private final List<String> eqFields = Arrays.asList("position", "profession");

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
        Query<Character> query = characterMapper.createQuery();
        Character character = BeanUtil.mapToBean(param, Character.class, true);
        CustomQueryUtil.setLikeField(character, query, likeFields);
        CustomQueryUtil.setEqField(character, query, eqFields);

        return ResultUtil.success(query.page(CustomPageUtil.getPageRequest(param)));
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
