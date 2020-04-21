package com.yifan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.entity.SysUserEntity;

public interface UserRepository extends JpaRepository<SysUserEntity, Integer>  {

    SysUserEntity findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
