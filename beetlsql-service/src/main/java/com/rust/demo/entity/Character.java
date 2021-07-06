package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Character {
    private String id;

    private String name;

    private String description;

    private Boolean canUseGeneralPotentialItem;

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

    private Boolean isNotObtainable;

    private Boolean isSpChar;

    private Boolean maxPotentialLevel;

    private Boolean rarity;

    private String profession;
}