package com.rust.demo.entity;

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

    private String codeName;

    private Integer rarity;

    private String job;

    private String gender;
}