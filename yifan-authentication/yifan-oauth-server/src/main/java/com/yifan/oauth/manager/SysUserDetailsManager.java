package com.yifan.oauth.manager;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.yifan.oauth.service.SysUserDetailsService;

@Service
public class SysUserDetailsManager implements UserDetailsManager {

    @Resource
    private SysUserDetailsService sysUserDetailsService;

    @Override
    public void createUser(UserDetails user) {
        sysUserDetailsService.createUser(user);
    }

    @Override
    public void updateUser(UserDetails user) {
        sysUserDetailsService.updateUser(user);
    }

    @Override
    public void deleteUser(String username) {
        sysUserDetailsService.deleteUser(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        sysUserDetailsService.changePassword(oldPassword, newPassword);
    }

    @Override
    public boolean userExists(String username) {
        return sysUserDetailsService.userExists(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sysUserDetailsService.loadUserByUsername(username);
    }
}
