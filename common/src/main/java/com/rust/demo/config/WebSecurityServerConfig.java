package com.rust.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityServerConfig extends WebSecurityConfigurerAdapter {

    @Lazy
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //http://127.0.0.1:8002/${server.servlet.context-path}/login
        //http://127.0.0.1:8002/${server.servlet.context-path}/oauth/authorize?client_id=client&response_type=code
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("admin123")).roles("ADMIN");
    }

    /**
     * 密码加密器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
