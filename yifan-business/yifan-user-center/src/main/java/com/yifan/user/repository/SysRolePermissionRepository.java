package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysRolePermissionEntity;

public interface SysRolePermissionRepository extends JpaRepository<SysRolePermissionEntity, Long> {
}
