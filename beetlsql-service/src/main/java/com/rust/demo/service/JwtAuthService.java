package com.rust.demo.service;

import com.rust.demo.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class JwtAuthService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserService userService;

    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String refreshToken(String token) {
        if (!jwtTokenUtil.isExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
