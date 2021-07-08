package com.rust.demo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("t_story")
public class Story extends Model<Story> {
    private Long id;

    private String charId;
    @TableField(condition = SqlCondition.LIKE)
    private String storyText;
    @TableField("unlock_type")
    private Integer unLockType;
    @TableField("unlock_param")
    private String unLockParam;
    @TableField("unlock_string")
    private String unLockString;
    @TableField(condition = SqlCondition.LIKE)
    private String storyTitle;
    @TableField("unlock_or_not")
    private Byte unLockorNot;
}