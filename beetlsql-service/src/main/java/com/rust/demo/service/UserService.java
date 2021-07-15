package com.rust.demo.service;

import com.rust.demo.entity.User;
import com.rust.demo.mapper.workspace01.UserMapper;
import com.rust.demo.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<String> roleCodes = userMapper.getRoleCode(user.getId());
        List<String> authorities = userMapper.getAuthority(roleCodes);
        roleCodes = roleCodes.stream().map(o -> "ROLE_" + o).collect(Collectors.toList());
        authorities.addAll(roleCodes);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setEnabled(user.getEnabled() == 1);
        userVO.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", authorities)
                )
        );
        return userVO;
    }

}
