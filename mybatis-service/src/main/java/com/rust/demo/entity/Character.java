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
@TableName("t_character")
public class Character extends Model<Character> {
    private String id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    @TableField(condition = SqlCondition.LIKE)
    private String description;

    private Boolean canUseGeneralPotentialItem;

    private String potentialItemId;
    @TableField(condition = SqlCondition.LIKE)
    private String nationId;
    @TableField(condition = SqlCondition.LIKE)
    private String groupId;
    @TableField(condition = SqlCondition.LIKE)
    private String teamId;

    private String displayNumber;

    private String tokenKey;
    @TableField(condition = SqlCondition.LIKE)
    private String appellation;
    @TableField(condition = SqlCondition.LIKE)
    private String position;
    @TableField(condition = SqlCondition.LIKE)
    private String tagList;

    private String itemUsage;

    private String itemDesc;

    private String itemObtainApproach;

    private Boolean isNotObtainable;

    private Boolean isSpChar;

    private Boolean maxPotentialLevel;

    private Boolean rarity;
    @TableField(condition = SqlCondition.LIKE)
    private String profession;
}