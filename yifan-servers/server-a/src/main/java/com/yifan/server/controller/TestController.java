package com.yifan.server.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class TestController {

    @GetMapping("test")
    public String test1(HttpServletRequest request) throws InterruptedException {
        Map<String, String[]> map = request.getParameterMap();

        System.out.println(JSONObject.toJSONString(map));

        Thread.sleep(510);
        return "test";
    }

}
