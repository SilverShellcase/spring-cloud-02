package com.rust.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ArknightsConfig {

    @Primary
    @Bean(name = "arknightsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.arknights")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

}