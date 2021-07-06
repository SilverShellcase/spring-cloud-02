package com.rust.demo.mapper.workspace01;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rust.demo.entity.Operator;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Deprecated
public interface OperatorMapper extends BaseMapper<Operator> {

    @Update("<script>" +
            " insert into operator values " +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">" +
            "  (#{item.id}, #{item.codeName}, #{item.rarity}, #{item.job}) " +
            "</foreach>" +
            "</script>")
    boolean insertBatch(List<Operator> operator);
}