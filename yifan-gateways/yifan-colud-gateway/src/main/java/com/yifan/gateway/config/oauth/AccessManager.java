package com.yifan.gateway.config.oauth;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import com.yifan.common.properties.CustomerSecurityProperties;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The type Access manager.
 *
 * @author wuyifan
 * @date 2020年04月27日 19:30
 */
@Slf4j
@Component
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        //请求资源
        String requestPath = exchange.getRequest().getURI().getPath();
        // 是否直接放行
        if (permitAll(requestPath)) {
            return Mono.just(new AuthorizationDecision(true));
        }

        return authenticationMono
                .map(auth -> new AuthorizationDecision(checkAuthorities(exchange, auth, requestPath)))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 是否直接放行
     *
     * @param requestPath the request path
     * @return the boolean
     * @author wuyifan
     * @date 2020年04月27日 19:30
     */
    private boolean permitAll(String requestPath) {
        return Arrays.stream(customerSecurityProperties.getIgnored())
                .anyMatch(r -> antPathMatcher.match(r, requestPath));
    }

    /**
     * 校验权限
     *
     * @param exchange    the exchange
     * @param auth        the auth
     * @param requestPath the request path
     * @return the boolean
     * @author wuyifan
     * @date 2020年04月27日 19:30
     */
    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        if(auth instanceof OAuth2Authentication){
            OAuth2Authentication authentication = (OAuth2Authentication) auth;
            String clientId = authentication.getOAuth2Request().getClientId();
            log.info("clientId is {}",clientId);
        }
        Object principal = auth.getPrincipal();
        log.info("用户信息:{}",principal.toString());
        return true;
    }
}

