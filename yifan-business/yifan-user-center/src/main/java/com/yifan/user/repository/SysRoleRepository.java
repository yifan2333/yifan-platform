package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysRoleEntity;

public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long> {
}
