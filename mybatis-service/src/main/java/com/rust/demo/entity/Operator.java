package com.rust.demo.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Deprecated
@Getter
@Setter
@NoArgsConstructor
public class Operator extends Model<Operator> {

    private String id;
    @TableField(condition = SqlCondition.LIKE)
    private String codeName;

    private Integer rarity;
    @TableField(condition = SqlCondition.LIKE)
    private String job;

    private String gender;
}