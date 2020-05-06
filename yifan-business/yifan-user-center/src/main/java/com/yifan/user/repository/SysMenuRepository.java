package com.yifan.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yifan.user.entity.SysMenuEntity;


public interface SysMenuRepository extends JpaRepository<SysMenuEntity, Long> {
}
