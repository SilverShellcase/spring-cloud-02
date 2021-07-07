package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Story;
import com.rust.demo.mapper.arknights.StoryMapper;
import org.springframework.stereotype.Service;

@Service
public class StoryService extends ServiceImpl<StoryMapper, Story> {
}
