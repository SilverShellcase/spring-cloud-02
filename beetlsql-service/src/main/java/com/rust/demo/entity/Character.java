package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_character")
public class Character {
    private String id;

    private String name;

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
}