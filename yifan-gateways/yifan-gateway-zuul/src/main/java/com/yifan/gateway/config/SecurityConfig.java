package com.yifan.gateway.config;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

import com.yifan.common.properties.CustomerSecurityProperties;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:10:08
 * ProjectName:Mirco-Service-Skeleton
 */
@Configuration
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(customerSecurityProperties.getIgnored()).permitAll()
                .anyRequest().authenticated();

    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(expressionHandler);
    }
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}
