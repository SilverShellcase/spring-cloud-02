package com.rust.demo.mapper.workspace01;

import com.rust.demo.entity.User;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.Param;
import org.beetl.sql.mapper.annotation.Template;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Template(" select id, username, password, enabled " +
            " from sys_user where username = #{username} ")
    User getByUsername(@Param("username") String username);

    @Template(" select r.role_code from sys_role r " +
            " left join sys_user_role ur on r.id = ur.role_id " +
            " left join sys_user u on u.id = ur.user_id " +
            " where u.id = #{userId} ")
    List<String> getRoleCode(@Param("userId") Integer userId);

    @Template(" select m.url from sys_menu m " +
            " left join sys_role_menu rm on m.id = rm.menu_id " +
            " left join sys_role r on r.id = rm.role_id " +
            " where r.role_code in (#{join(roleCodes)}) ")
    List<String> getAuthority(@Param("roleCodes") List<String> roleCodes);
}
