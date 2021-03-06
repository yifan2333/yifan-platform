package com.yifan.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@ComponentScan(basePackages = "com.yifan.redis")
public class RedisConfig {

    @Bean(name = "jsonRedisTemplate")
    public JsonRedisTemplate jsonRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new JsonRedisTemplate(connectionFactory);
    }
}
