package com.yifan.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users")
    public Authentication user(Authentication user) {
        return user;
    }

    @RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
    
}
