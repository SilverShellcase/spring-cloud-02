package com.rust.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.rust.demo.mapper.workspace01", sqlSessionFactoryRef = "workspace01SqlSessionFactory")
public class Workspace01Config {

    @Bean(name = "workspace01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.workspace01")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "workspace01TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("workspace01DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "workspace01SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("workspace01DataSource") DataSource dataSource) throws Exception {
        GlobalConfig config = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setUpdateStrategy(FieldStrategy.NOT_EMPTY);
        dbConfig.setInsertStrategy(FieldStrategy.NOT_EMPTY);
        dbConfig.setWhereStrategy(FieldStrategy.NOT_EMPTY);
        config.setDbConfig(dbConfig);

        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/rust/demo/mapper/workspace01/*.xml"));
        bean.setGlobalConfig(config);
        return bean.getObject();
    }

}
