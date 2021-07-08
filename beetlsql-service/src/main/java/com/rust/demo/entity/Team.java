package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_team")
public class Team {

    private String id;

    private Integer orderNum;

    private Integer powerLevel;

    private String powerName;

    private String powerCode;

    private String color;

    private Byte isLimited;

    private Byte isRaw;
}