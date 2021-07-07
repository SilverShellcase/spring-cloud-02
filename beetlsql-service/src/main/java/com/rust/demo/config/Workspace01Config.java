package com.rust.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Workspace01Config {

    @Bean(name = "workspace01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.workspace01")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

}
