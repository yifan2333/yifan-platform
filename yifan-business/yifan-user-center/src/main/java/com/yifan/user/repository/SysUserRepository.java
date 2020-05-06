package com.yifan.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysUserEntity;

public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {

    SysUserEntity findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
