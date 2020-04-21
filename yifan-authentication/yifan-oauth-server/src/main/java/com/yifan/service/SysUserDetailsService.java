package com.yifan.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yifan.dto.SysUserDto;
import com.yifan.feign.UserCenterFeign;
import com.yifan.result.ActionResult;

@Service
public class SysUserDetailsService {

    @Resource
    private UserCenterFeign userCenterFeign;

    public void createUser(UserDetails user) {

    }

    public void updateUser(UserDetails user) {

    }

    public void deleteUser(String username) {

    }

    public void changePassword(String oldPassword, String newPassword) {

    }

    public boolean userExists(String username) {
        return false;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ActionResult<SysUserDto> actionResult = userCenterFeign.userByUsername(username);
        if (!actionResult.successful()) {
            throw new UsernameNotFoundException("");
        }
        return actionResult.getData();
    }

}
