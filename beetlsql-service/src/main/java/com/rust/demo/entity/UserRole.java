package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.annotation.entity.Table;

@Setter
@Getter
@Table(name = "sys_user_role")
public class UserRole {
    /**
     *
     */
    private Integer id;

    /**
     * 角色自增id
     */
    private Integer roleId;

    /**
     * 用户自增id
     */
    private Integer userId;

}

