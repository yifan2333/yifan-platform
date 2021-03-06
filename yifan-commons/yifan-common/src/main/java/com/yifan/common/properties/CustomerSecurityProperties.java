package com.yifan.common.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("application.security.oauth")
@EnableConfigurationProperties(CustomerSecurityProperties.class)
public class CustomerSecurityProperties {

    /**
     * 登录请求的路径, 默认值 /authorization/form
     */
    private String loginProcessingUrl = "/authorization/form";

    /**
     * 忽略的路径
     */
    private static final String[] ENDPOINTS = {"/**/*.jpg", "/**/*.txt", "/**/*.json", "/**/*.html",
            "/**/*.css", "/**/*.js", "/**/*.md", "/v2/api-docs-ext", "/csrf", "/static/**",
            "/**/actuator/health", "/**/actuator/env", "/**/actuator/metrics/**",
            "/**/actuator/trace", "/**/actuator/dump", "/**/actuator/jolokia", "/**/actuator/info",
            "/**/actuator/logfile", "/**/actuator/refresh", "/**/actuator/flyway", "/**/actuator/liquibase",
            "/**/actuator/heapdump", "/**/actuator/loggers", "/**/actuator/auditevents", "/**/actuator/env/PID",
            "/**/actuator/jolokia/**", "/**/actuator/archaius/**", "/**/actuator/beans/**", "/**/actuator/httptrace",
            "/**/v2/api-docs/**", "/**/swagger-ui.html", "/**/swagger-resources/**", "/**/webjars/**", "/**/druid/**",
            "/**/actuator/hystrix.stream", "/**/actuator/hystrix.stream**/**", "/**/turbine.stream", "/**/turbine.stream**/**",
            "/**/hystrix", "/**/hystrix.stream", "/**/hystrix/**", "/**/hystrix/**/**", "/**/proxy.stream/**", "/**/favicon.ico",
            "/oauth/login"};

    private String[] ignored;

    /**
     * Get ignored string [ ].
     *
     * @return the string [ ]
     * @author wuyifan
     * @date 2020年04月26日 17:11
     */
    public String[] getIgnored() {
        if (ignored == null || ignored.length == 0) {
            return ENDPOINTS;
        }

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(ENDPOINTS));
        list.addAll(Arrays.asList(ignored));

        return list.toArray(new String[0]);
    }

    /**
     * Sets ignored.
     *
     * @param ignored the ignored
     * @author wuyifan
     * @date 2020年04月26日 17:11
     */
    public void setIgnored(String[] ignored) {
        this.ignored = ignored;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }
}
