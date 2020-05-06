package com.yifan.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yifan.user.entity.SysPermissionEntity;
import com.yifan.user.entity.SysRolePermissionEntity;
import com.yifan.user.repository.SysPermissionRepository;
import com.yifan.user.repository.SysRolePermissionRepository;

@Service
public class SysPermissionService {

    @Resource
    private SysRolePermissionRepository sysRolePermissionRepository;

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public List<SysPermissionEntity> findPermissionByRoleId(Long roleId) {
        List<SysPermissionEntity> permissions = new ArrayList<>();
        if (roleId == null) {
            return permissions;
        }
        SysRolePermissionEntity rolePermissionParam = new SysRolePermissionEntity();
        rolePermissionParam.setRoleId(roleId);
        rolePermissionParam.setIsDeleted(0);
        List<SysRolePermissionEntity> rolePermissions = sysRolePermissionRepository.findAll(Example.of(rolePermissionParam));

        rolePermissions.forEach(rolePermission -> {
            SysPermissionEntity permissionParam = new SysPermissionEntity();
            permissionParam.setPermissionId(rolePermission.getPermissionId());
            permissionParam.setIsDeleted(0);
            List<SysPermissionEntity> list = sysPermissionRepository.findAll(Example.of(permissionParam));

            if (!CollectionUtils.isEmpty(list)) {
                permissions.addAll(list);
            }
        });

        return permissions;
    }
}
