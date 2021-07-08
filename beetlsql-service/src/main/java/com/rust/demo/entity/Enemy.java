package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_enemy")
public class Enemy {

    private String id;

    private String enemyIndex;

    private String enemyTags;

    private Integer sortId;

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

    private Byte isInvalidKilled;
}