package com.yifan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.entity.SysRoleEntity;

public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Integer> {
}
