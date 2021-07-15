package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.beetl.sql.annotation.entity.Table;

import java.util.Date;

@Setter
@Getter
@Table(name = "sys_role")
public class Role {
    /**
     *
     */
    private Integer id;

    /**
     * 角色名称(汉字)
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色的英文code.如：ADMIN
     */
    private String roleCode;

    /**
     * 角色顺序
     */
    private Integer sort;

    /**
     * 0表示可用
     */
    private Integer status;

    /**
     * 角色的创建日期
     */
    private Date createTime;

}

