package com.rust.demo.entity;

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

    private String drawName;

    private String infoName;

    private String codeName;

    private String gender;

    private String combatExperience;

    private String placeOfBirth;

    private String dateOfBirth;

    private String race;

    private String height;

    private String infectionStatus;

    private String physicalStrength;

    private String mobility;

    private String physicalResilience;

    private String tacticalAcumen;

    private String combatSkill;

    private String originiumArtsAssilimation;
}