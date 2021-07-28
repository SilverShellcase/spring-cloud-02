package com.rust.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisDBConfig {

    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis1.password}")
    private String password1;

    /**
     * 配置第一个数据源的
     */
    @Bean("redisConfig")
    @ConfigurationProperties(prefix = "spring.redis", ignoreInvalidFields = true)
    public RedisStandaloneConfiguration redisConfig() {
        return new RedisStandaloneConfiguration();
    }

    /**
     * 配置第二个数据源
     */
    @Bean("redisConfig1")
    @ConfigurationProperties(prefix = "spring.redis1", ignoreInvalidFields = true)
    public RedisStandaloneConfiguration redisConfig1() {
        return new RedisStandaloneConfiguration();
    }

    /**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     */
    @Primary
    @Bean("factory")
    public LettuceConnectionFactory factory(@Qualifier("redisConfig") RedisStandaloneConfiguration redisConfig) {
        redisConfig.setPassword(password);
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean("factory1")
    public LettuceConnectionFactory factory1(@Qualifier("redisConfig1") RedisStandaloneConfiguration redisConfig1) {
        redisConfig1.setPassword(password1);
        return new LettuceConnectionFactory(redisConfig1);
    }

    @Primary
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> stringRedisTemplate(@Qualifier("factory") LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean("redisTemplate1")
    public RedisTemplate<String, Object> stringRedisTemplate1(@Qualifier("factory1") LettuceConnectionFactory factory1) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory1);

        template.setConnectionFactory(factory1);
        //key序列化方式
        template.setKeySerializer(RedisSerializer.string());
        //value序列化
        template.setValueSerializer(RedisSerializer.json());
        //value hashmap序列化
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }

}