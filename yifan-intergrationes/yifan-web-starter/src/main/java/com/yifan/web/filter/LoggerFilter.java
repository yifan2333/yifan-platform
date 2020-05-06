package com.yifan.web.filter;

import java.io.IOException;

import javax.annotation.Nullable;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 日志拦截器
 *
 * @author wuyifan
 * @date 2020年03月13日 16:48
 */
@Component
public class LoggerFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    @Nullable HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("Session start");
        long startTime = System.currentTimeMillis();
        logger.info("Request URL: {}", httpServletRequest.getRequestURL());
        logger.info("client-type: {}", httpServletRequest.getHeader("client-type"));

        String token = httpServletRequest.getHeader("token");
        logger.info("token: {}", token);

        filterChain.doFilter(httpServletRequest, httpServletResponse);

        long executeTime = System.currentTimeMillis() - startTime;
        logger.info("Session end, execute time: {}", executeTime);
    }
}
