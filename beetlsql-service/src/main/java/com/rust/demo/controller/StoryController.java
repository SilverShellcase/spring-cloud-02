package com.rust.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rust.demo.entity.Story;
import com.rust.demo.mapper.arknights.StoryMapper;
import com.rust.demo.util.CustomPageUtil;
import com.rust.demo.util.QueryUtil;
import org.beetl.sql.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class StoryController {

    private final List<String> likeFields = Arrays.asList("storyText", "storyTitle");
    private final List<String> eqFields = Arrays.asList("charId", "unlockType", "unlockParam", "unlockOrNot");

    @Resource
    private StoryMapper storyMapper;

    @PostMapping("/story/list")
    public Object list(@RequestBody Story story) {
        Query<Story> query = storyMapper.createQuery();
        QueryUtil.setLikeField(story, query, likeFields);
        QueryUtil.setEqField(story, query, eqFields);

        return query.select();
    }

    @PostMapping("/story/page")
    public Object page(@RequestBody Map<String, Object> param) {
        Query<Story> query = storyMapper.createQuery();
        Story story = BeanUtil.mapToBean(param, Story.class, true);
        QueryUtil.setLikeField(story, query, likeFields);
        QueryUtil.setEqField(story, query, eqFields);

        return query.page(CustomPageUtil.getPageRequest(param));
    }
}
