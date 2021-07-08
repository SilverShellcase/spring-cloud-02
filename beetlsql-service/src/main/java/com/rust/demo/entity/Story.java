package com.rust.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.beetl.sql.annotation.entity.Column;
import org.beetl.sql.annotation.entity.Table;

@Getter
@Setter
@ToString
@Table(name = "t_story")
public class Story {
    private Long id;

    private String charId;

    private String storyText;
    @Column("unlock_type")
    private Integer unLockType;
    @Column("unlock_param")
    private String unLockParam;
    @Column("unlock_string")
    private String unLockString;

    private String storyTitle;
    @Column("unlock_or_not")
    private Boolean unLockorNot;
}