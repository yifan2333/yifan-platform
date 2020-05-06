package com.yifan.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yifan.common.entity.security.SysUserEntity;

public abstract class BaseController {
	protected Long getUserId() {
		return getUser().getUserId();
	}

	protected String getUserName() {
		return getUser().getUsername();
	}

	protected SysUserEntity getUser() {
		return (SysUserEntity)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}