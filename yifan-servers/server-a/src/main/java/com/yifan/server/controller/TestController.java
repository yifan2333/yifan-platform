package com.yifan.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;

@RestController
public class TestController {

    @GetMapping("test")
    @SentinelResource
    public String test1(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();

        System.out.println(JSONObject.toJSONString(map));
        return "test";
    }

}
