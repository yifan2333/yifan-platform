package com.yifan.security.entrypoint;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.yifan.common.utils.ResponseUtils;
import com.yifan.web.result.ActionResult;
import com.yifan.web.result.ResultType;

import lombok.extern.slf4j.Slf4j;

/** 
 * AuthenticationException 是在用户认证的时候出现错误时抛出的异常
  * 系统用户不存在，被锁定，凭证失效，密码错误等认证过程中出现的异常都由 AuthenticationException 处理。 401
 *
 * @author: wuyifan
 * @since: 2019年12月10日 下午3:11
 * @version 1.0
 */
@Slf4j
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("认证失败", authException);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseUtils.responseJsonWriter(response, new ActionResult.Builder<>().resultType(ResultType.UNAUTHORIZED).build());
    }
}
