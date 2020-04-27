package com.yifan.resource.controller;

import java.security.Principal;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yifan.security.controller.BaseController;

@RestController
@RequestMapping("/test")
public class OauthController extends BaseController {

    /**
     * 获取当前登录的用户信息
     *
     * @param principal 用户信息
     * @return http 响应
     */
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('permission:post/permissions')")
    public HttpEntity<?> oauthMe(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @param principal 用户信息
     * @return http 响应
     */
    @GetMapping("/me2")
    public HttpEntity<?> oauthMe2(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @param principal 用户信息
     * @return http 响应
     */
    @GetMapping("/me3")
    @PreAuthorize("hasAuthority('permission:post/permissions2')")
    public HttpEntity<?> oauthMe3(Principal principal) {
        return ResponseEntity.ok(principal);
    }


    @GetMapping("/userId")
    public HttpEntity<?> userId() {
        return ResponseEntity.ok(getUserId());
    }
    @GetMapping("/userName")
    public HttpEntity<?> userName() {
        return ResponseEntity.ok(getUserName());
    }
    @GetMapping("/user")
    public HttpEntity<?> user() {
        return ResponseEntity.ok(getUser());
    }


}
