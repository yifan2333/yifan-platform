package com.yifan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yifan.entity.SysRoleEntity;
import com.yifan.entity.SysRoleUserEntity;
import com.yifan.repository.SysRoleRepository;
import com.yifan.repository.SysRoleUserRepository;

@Service
public class SysRoleService {

    @Resource
    public SysRoleUserRepository sysRoleUserRepository;
    @Resource
    public SysRoleRepository sysRoleRepository;

    public List<SysRoleEntity> findRolesByUserId(Long userId){
        List<SysRoleEntity> roles = new ArrayList<>();
        if (userId == null) {
            return roles;
        }

        SysRoleUserEntity roleUserParam = new SysRoleUserEntity();
        roleUserParam.setUserId(userId);
        roleUserParam.setIsDeleted(0);
        List<SysRoleUserEntity> roleUsers = sysRoleUserRepository.findAll(Example.of(roleUserParam));

        roleUsers.forEach(roleUser -> {
            SysRoleEntity roleParam = new SysRoleEntity();
            roleParam.setId(roleUser.getRoleId());
            roleParam.setIsDeleted(0);
            List<SysRoleEntity> list = sysRoleRepository.findAll(Example.of(roleParam));

            if (!CollectionUtils.isEmpty(list)) {
                roles.addAll(list);
            }
        });

        return roles;
    }

}
