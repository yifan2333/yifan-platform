package com.yifan.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
public class ServerAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerAApplication.class, args);
    }

    @RestController
    public static class test {

        @GetMapping("test")
        public String test1(HttpServletRequest request) {
            Map<String, String[]> map = request.getParameterMap();

            System.out.println(JSONObject.toJSONString(map));
            return "test";
        }

    }
}
