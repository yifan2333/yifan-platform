package com.yifan.oauth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.yifan.common.entity.security.SysUserEntity;


public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> additionalInfo = new HashMap<>();
        SysUserEntity user = (SysUserEntity) authentication.getPrincipal();
        user.setPassword("*******");
        additionalInfo.put("USER",user);
        defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);
        return super.enhance(defaultOAuth2AccessToken,authentication);
    }
}