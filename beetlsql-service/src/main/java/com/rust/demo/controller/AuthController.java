package com.rust.demo.controller;

import com.rust.demo.service.JwtAuthService;
import com.rust.demo.util.ResultUtil;
import com.rust.demo.vo.UserVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthController {

    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping("/auth/login")
    public Object login(@RequestBody UserVO userVO) {
        if (!StringUtils.hasText(userVO.getUsername()) || !StringUtils.hasText(userVO.getPassword())) {
            return ResultUtil.failed("用户名或密码不能为空");
        }
        return jwtAuthService.login(userVO.getUsername(), userVO.getPassword());
    }

    @PostMapping("/auth/refreshToken")
    public Object refreshToken(@RequestHeader("${jwt.header}") String token) {
        return jwtAuthService.refreshToken(token);
    }

}
