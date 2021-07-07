package com.rust.demo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("t_team")
public class Team extends Model<Team> {

    private String id;

    private Integer orderNum;

    private Integer powerLevel;
    @TableField(condition = SqlCondition.LIKE)
    private String powerName;
    @TableField(condition = SqlCondition.LIKE)
    private String powerCode;

    private String color;

    private Boolean isLimited;

    private Boolean isRaw;
}