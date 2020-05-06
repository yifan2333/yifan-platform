package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysPermissionEntity;

public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity, Long> {
}
