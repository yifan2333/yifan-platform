package com.yifan.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yifan.user.entity.SysPermissionEntity;
import com.yifan.user.entity.SysRoleEntity;
import com.yifan.user.entity.SysUserEntity;
import com.yifan.user.repository.SysUserRepository;

@Service
public class SysUserService {

    @Resource
    public SysUserRepository sysUserRepository;
    @Resource
    public SysRoleService sysRoleService;
    @Resource
    public SysPermissionService sysPermissionService;

    public com.yifan.common.entity.security.SysUserEntity getSysUser(String userName) {

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

        return getUser(user, roles, permissions);
    }

    private com.yifan.common.entity.security.SysUserEntity getUser(SysUserEntity entity, List<SysRoleEntity> roleEntities, List<SysPermissionEntity> permissionEntities) {
        com.yifan.common.entity.security.SysUserEntity userDto = new com.yifan.common.entity.security.SysUserEntity();
        userDto.setUserId(entity.getUserId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setNickname(entity.getNickname());
        userDto.setHeadImgUrl(entity.getHeadImgUrl());
        userDto.setPhone(entity.getPhone());
        userDto.setSex(entity.getSex());
        userDto.setType(entity.getType());

        if (!CollectionUtils.isEmpty(roleEntities)) {
            List<com.yifan.common.entity.security.SysRoleEntity> roles = roleEntities.stream().map(roleEntity -> {
                com.yifan.common.entity.security.SysRoleEntity dto = new com.yifan.common.entity.security.SysRoleEntity();
                dto.setId(roleEntity.getId());
                dto.setCode(roleEntity.getCode());
                dto.setName(roleEntity.getName());
                return dto;
            }).collect(Collectors.toList());

            userDto.setSysRoles(roles);
        }

        if (!CollectionUtils.isEmpty(permissionEntities)) {
            List<com.yifan.common.entity.security.SysPermissionEntity> permissions = permissionEntities.stream().map(permissionEntity -> {
                com.yifan.common.entity.security.SysPermissionEntity dto = new com.yifan.common.entity.security.SysPermissionEntity();
                dto.setPermissionId(permissionEntity.getPermissionId());
                dto.setPermission(permissionEntity.getPermission());
                dto.setName(permissionEntity.getName());
                return dto;
            }).collect(Collectors.toList());

            userDto.setPermissions(permissions);
        }
        return userDto;
    }

}
