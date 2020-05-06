package com.yifan.oauth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

    @GetMapping(value = "user", produces = "application/json")
    public Principal getUser(Principal principal) {
        log.info("auth : {}", principal);
        return principal;
    }
}
