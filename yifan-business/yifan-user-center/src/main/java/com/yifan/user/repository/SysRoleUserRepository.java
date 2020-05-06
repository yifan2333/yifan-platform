package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysRoleUserEntity;

public interface SysRoleUserRepository extends JpaRepository<SysRoleUserEntity, Long> {
}
