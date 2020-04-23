package com.yifan.controller;

import java.security.Principal;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 19-7-15 上午10:07
 */
@RestController
@RequestMapping("/test")
public class OauthController {

    /**
     * 获取当前登录的用户信息
     *
     * @param principal 用户信息
     * @return http 响应
     */
    @GetMapping("/me")
    public HttpEntity<?> oauthMe(Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
