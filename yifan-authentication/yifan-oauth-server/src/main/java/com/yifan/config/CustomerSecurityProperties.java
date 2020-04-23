package com.yifan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("application.security.oauth")
public class CustomerSecurityProperties {

    /**
     * 登录请求的路径, 默认值 /authorization/form
     */
    private String loginProcessingUrl = "/authorization/form";

}
