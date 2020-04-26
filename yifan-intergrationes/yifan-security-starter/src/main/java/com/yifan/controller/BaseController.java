package com.yifan.controller;

import javax.annotation.Resource;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.yifan.dto.SysUserDto;
import com.yifan.utils.TokenUtils;

public abstract class BaseController {

	@Resource
	private TokenStore tokenStore;

	protected Integer getUserId() {
		return getUser().getUserId();
	}

	protected String getUserName() {
		return getUser().getUsername();
	}

	protected SysUserDto getUser() {
		String token = TokenUtils.getToken();
		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(token);
		return (SysUserDto) auth2Authentication.getPrincipal();
	}
}