package com.yifan.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtils {

    public static void responseJsonWriter(HttpServletResponse response, Object object) throws IOException {

        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(JSONObject.toJSONString(object));
        printWriter.flush();
        printWriter.close();
    }

}
