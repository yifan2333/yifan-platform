package com.yifan.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableFeignClients
@EnableRedisHttpSession
public class OauthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthCenterApplication.class, args);
    }

}
