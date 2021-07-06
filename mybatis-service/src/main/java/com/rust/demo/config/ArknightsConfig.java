package com.rust.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.rust.demo.mapper.arknights", sqlSessionFactoryRef = "arknightsSqlSessionFactory")
public class ArknightsConfig {

    @Bean(name = "arknightsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.arknights")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "arknightsTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("arknightsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "arknightsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("arknightsDataSource") DataSource dataSource) throws Exception {
        GlobalConfig config = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setUpdateStrategy(FieldStrategy.NOT_EMPTY);
        dbConfig.setInsertStrategy(FieldStrategy.NOT_EMPTY);
        dbConfig.setWhereStrategy(FieldStrategy.NOT_EMPTY);
        config.setDbConfig(dbConfig);

        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/rust/demo/mapper/arknights/*.xml"));
        bean.setGlobalConfig(config);
        return bean.getObject();
    }

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }

    @Bean
    public MybatisPlusProperties mybatisPlusProperties() {
        return new MybatisPlusProperties();
    }
}
