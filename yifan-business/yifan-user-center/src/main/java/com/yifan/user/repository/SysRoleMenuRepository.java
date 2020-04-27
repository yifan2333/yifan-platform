package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysRoleMenuEntity;

public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuEntity, Long> {
}
