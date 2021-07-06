package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Enemy {

    private String id;

    private String enemyIndex;

    private String enemyTags;

    private Byte sortId;

    private String name;

    private String enemyRace;

    private String enemyLevel;

    private String description;

    private String attackType;

    private String endure;

    private String attack;

    private String defence;

    private String resistance;

    private String ability;

    private Boolean isInvalidKilled;
}