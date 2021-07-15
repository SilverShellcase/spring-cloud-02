package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu {
    /**
     *
     */
    private Integer id;

    /**
     * 父菜单ID
     */
    private Integer menuPid;

    /**
     * 当前菜单所有父菜单
     */
    private String menuPids;

    /**
     * 0:不是叶子节点，1:是叶子节点
     */
    private Byte isLeaf;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 跳转URL
     */
    private String url;

    /**
     *
     */
    private String icon;

    /**
     *
     */
    private String iconColor;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 菜单层级
     */
    private Byte level;

    /**
     * 0:启用,1:禁用
     */
    private Byte status;
}

