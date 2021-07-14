package com.rust.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate1")
    private RedisTemplate redisTemplate1;

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("test", "noToken");
        redisTemplate1.opsForValue().set("demo", "access");
    }
}
