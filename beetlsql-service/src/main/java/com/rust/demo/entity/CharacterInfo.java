package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_character_info")
public class CharacterInfo {
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