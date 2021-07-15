package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.annotation.entity.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@Table(name = "sys_user")
public class User {
    /**
     *
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 组织id
     */
    private Integer orgId;

    /**
     * 0无效用户，1是有效用户
     */
    private Integer enabled;

    /**
     * 手机号
     */
    private String phone;

    /**
     * email
     */
    private String email;
}

