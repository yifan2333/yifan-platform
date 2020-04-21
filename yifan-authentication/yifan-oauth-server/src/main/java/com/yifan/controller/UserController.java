package com.yifan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @GetMapping("user")
    public Authentication getUser(Authentication authentication) {
        log.info("auth : {}", authentication);
        return authentication;

    }
}
