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
@TableName("t_character_info")
public class CharacterInfo extends Model<CharacterInfo> {
    private String id;
    @TableField(condition = SqlCondition.LIKE)
    private String drawName;
    @TableField(condition = SqlCondition.LIKE)
    private String infoName;
    @TableField(condition = SqlCondition.LIKE)
    private String codeName;

    private String gender;

    private String combatExperience;
    @TableField(condition = SqlCondition.LIKE)
    private String placeOfBirth;

    private String dateOfBirth;
    @TableField(condition = SqlCondition.LIKE)
    private String race;

    private String height;
    @TableField(condition = SqlCondition.LIKE)
    private String infectionStatus;

    private String physicalStrength;

    private String mobility;

    private String physicalResilience;

    private String tacticalAcumen;

    private String combatSkill;

    private String originiumArtsAssilimation;
}