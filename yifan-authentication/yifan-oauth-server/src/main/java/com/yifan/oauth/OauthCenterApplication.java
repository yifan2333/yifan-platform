package com.yifan.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OauthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthCenterApplication.class, args);
    }

}
