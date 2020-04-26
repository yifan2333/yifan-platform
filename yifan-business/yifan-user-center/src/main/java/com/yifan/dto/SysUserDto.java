package com.yifan.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.yifan.entity.SysPermissionEntity;
import com.yifan.entity.SysRoleEntity;
import com.yifan.entity.SysUserEntity;

import lombok.Data;

@Data
public class SysUserDto {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String headImgUrl;
    private String phone;
    private Integer sex;
    private String type;

    private List<SysRoleDto> roles;

    private List<SysPermissionDto> permissions;

    public static SysUserDto getUser(SysUserEntity entity, List<SysRoleEntity> roleEntities, List<SysPermissionEntity> permissionEntities) {
        SysUserDto userDto = new SysUserDto();
        userDto.setUserId(entity.getUserId());
        userDto.setUsername(entity.getUsername());
        userDto.setPassword(entity.getPassword());
        userDto.setNickname(entity.getNickname());
        userDto.setHeadImgUrl(entity.getHeadImgUrl());
        userDto.setPhone(entity.getPhone());
        userDto.setSex(entity.getSex());
        userDto.setType(entity.getType());

        if (!CollectionUtils.isEmpty(roleEntities)) {
            List<SysRoleDto> roles = roleEntities.stream().map(roleEntity -> {
                SysRoleDto dto = new SysRoleDto();
                dto.setId(roleEntity.getId());
                dto.setCode(roleEntity.getCode());
                dto.setName(roleEntity.getName());
                return dto;
            }).collect(Collectors.toList());

            userDto.setRoles(roles);
        }

        if (!CollectionUtils.isEmpty(permissionEntities)) {
            List<SysPermissionDto> permissions = permissionEntities.stream().map(permissionEntity -> {
                SysPermissionDto dto = new SysPermissionDto();
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
