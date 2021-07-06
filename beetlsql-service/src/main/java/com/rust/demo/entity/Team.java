package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Team {

    private String id;

    private Integer orderNum;

    private Integer powerLevel;

    private String powerName;

    private String powerCode;

    private String color;

    private Boolean isLimited;

    private Boolean isRaw;
}