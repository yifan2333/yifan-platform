package com.yifan.gateway.config.oauth;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveRedisAuthenticationManager implements ReactiveAuthenticationManager {

    private final TokenStore tokenStore;

    public ReactiveRedisAuthenticationManager(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .cast(String.class)
                .flatMap((accessToken ->{
                    log.info("accessToken is :{}",accessToken);
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    if(oAuth2AccessToken == null){
                        return Mono.error(new InvalidTokenException("错误的Access Token"));
                    }else if(oAuth2AccessToken.isExpired()){
                        return Mono.error(new InvalidTokenException("Access Token 已经失效"));
                    }

                    OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);

                    if(auth2Authentication == null){
                        return Mono.error(new InvalidTokenException("Access Token 无效!"));
                    }else {
                        return Mono.just(auth2Authentication);
                    }
                })).cast(Authentication.class);
    }
}