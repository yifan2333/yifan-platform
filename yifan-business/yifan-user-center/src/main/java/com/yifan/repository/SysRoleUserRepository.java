package com.yifan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.entity.SysRoleUserEntity;

public interface SysRoleUserRepository extends JpaRepository<SysRoleUserEntity, Integer> {
}
