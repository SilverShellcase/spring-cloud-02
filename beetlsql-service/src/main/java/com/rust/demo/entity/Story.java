package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_story")
public class Story {
    private Long id;

    private String charId;

    private String storyText;

    private Integer unlockType;

    private String unlockParam;

    private String unlockString;

    private String storyTitle;

    private Byte unlockOrNot;
}