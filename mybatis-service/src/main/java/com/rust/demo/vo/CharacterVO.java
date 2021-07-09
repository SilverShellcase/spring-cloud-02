package com.rust.demo.vo;

import com.rust.demo.entity.Story;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CharacterVO {

    private String id;

    private String codeName;

    private String description;

    private Byte canUseGeneralPotentialItem;

    private String potentialItemId;

    private String nationId;

    private String groupId;

    private String teamId;

    private String displayNumber;

    private String tokenKey;

    private String appellation;

    private String position;

    private String tagList;

    private String itemUsage;

    private String itemDesc;

    private String itemObtainApproach;

    private Byte isNotObtainable;

    private Byte isSpChar;

    private Byte maxPotentialLevel;

    private Byte rarity;

    private String profession;

    private String drawName;

    private String infoName;

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

    private List<Story> storyList;
}
