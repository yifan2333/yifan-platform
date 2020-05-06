package com.yifan.oauth.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yifan.common.properties.CustomerSecurityProperties;

@Controller
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;

    @GetMapping("login")
    public String loginView(Model model) {
        model.addAttribute("action", customerSecurityProperties.getLoginProcessingUrl());
        return "form-login";
    }

}
