package com.yifan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yifan.dto.SysUserDto;
import com.yifan.entity.SysPermissionEntity;
import com.yifan.entity.SysRoleEntity;
import com.yifan.entity.SysUserEntity;
import com.yifan.repository.SysUserRepository;

@Service
public class SysUserService {

    @Resource
    public SysUserRepository sysUserRepository;
    @Resource
    public SysRoleService sysRoleService;
    @Resource
    public SysPermissionService sysPermissionService;

    public SysUserDto getSysUser(String userName) {

        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        SysUserEntity user = sysUserRepository.findByUsername(userName);
        if (user == null) {
            return null;
        }
        List<SysRoleEntity> roles = sysRoleService.findRolesByUserId(user.getUserId());
        List<SysPermissionEntity> permissions = new ArrayList<>();
        roles.forEach(sysRoleEntity -> {
            List<SysPermissionEntity> list = sysPermissionService.findPermissionByRoleId(sysRoleEntity.getId());
            if (!CollectionUtils.isEmpty(list)) {
                permissions.addAll(list);
            }
        });

        return SysUserDto.getUser(user, roles, permissions);
    }

}
