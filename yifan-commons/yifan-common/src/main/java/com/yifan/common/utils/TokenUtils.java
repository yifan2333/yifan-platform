package com.yifan.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokenUtils {

    public static final String Authorization = "Authorization" ;

    public static String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String header = request.getHeader(Authorization);

        return StringUtils.isBlank(StringUtils.substringAfter(header, OAuth2AccessToken.BEARER_TYPE + " ")) ?
                request.getParameter(OAuth2AccessToken.ACCESS_TOKEN) :
                StringUtils.substringAfter(header, OAuth2AccessToken.BEARER_TYPE + " ");
    }

}
