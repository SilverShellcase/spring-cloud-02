package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.entity.Character;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.entity.Story;
import com.rust.demo.mapper.arknights.CharacterInfoMapper;
import com.rust.demo.mapper.arknights.CharacterMapper;
import com.rust.demo.mapper.arknights.StoryMapper;
import com.rust.demo.util.CustomBeanUtil;
import com.rust.demo.util.CustomPageUtil;
import com.rust.demo.util.QueryUtil;
import com.rust.demo.util.ResultUtil;
import com.rust.demo.vo.CharacterVO;
import org.beetl.sql.core.query.LambdaQuery;
import org.beetl.sql.core.query.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/character")
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

    @PostMapping("/list")
    public Object list(@RequestBody Character entity) {
        return characterMapper.selectList(entity);
    }

    @PostMapping("/page")
    public Object page(@RequestBody Map<String, Object> param) {
        Query<Character> query = characterMapper.createQuery();
        Character character = BeanUtil.mapToBean(param, Character.class, true);
        QueryUtil.setLikeField(character, query, likeFields);
        QueryUtil.setEqField(character, query, eqFields);
        return query.page(CustomPageUtil.getPageRequest(param));
    }

    @PostMapping("/get")
    public Object get(@RequestBody Character entity) {
        Character character = characterMapper.single(entity.getId());
        CharacterInfo characterInfo = characterInfoMapper.single(entity.getId());
        LambdaQuery<Story> lambdaQuery = storyMapper.createLambdaQuery();
        CharacterVO characterVO = new CharacterVO();
        CustomBeanUtil.copyProperties(character, characterVO);
        CustomBeanUtil.copyProperties(characterInfo, characterVO);
        List<Story> storyList = lambdaQuery.andEq(Story::getCharId, characterVO.getId()).select();
        characterVO.setStoryList(storyList);
        return characterVO;
    }

    @Transactional("arknightsTransactionManager")
    @PostMapping("/update")
    public Object update(@RequestBody CharacterVO entity) {
        Character character = new Character();
        CustomBeanUtil.copyProperties(entity, character);
        characterMapper.updateById(character);
        CharacterInfo characterInfo = new CharacterInfo();
        CustomBeanUtil.copyProperties(entity, characterInfo);
        characterInfoMapper.updateById(characterInfo);
        return ResultUtil.success();
    }

    @Transactional("arknightsTransactionManager")
    @PostMapping("/delete")
    public Object delete(@RequestBody Character entity) {
        characterMapper.deleteById(entity.getId());
        characterInfoMapper.deleteById(entity.getId());
        return ResultUtil.success();
    }
}
