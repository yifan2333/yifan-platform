package com.yifan.redis.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

/**
 * The type Json redis template.
 *
 * @author wuyifan
 * @date 2020年04月17日 19:11
 */
public class JsonRedisTemplate extends RedisTemplate<String, Object> {

	/**
	 * Instantiates a new Json redis template.
	 *
	 * @param connectionFactory the connection factory
	 * @author wuyifan
	 * @date 2020年04月17日 19:11
	 */
	JsonRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisSerializer<String> stringSerializer = RedisSerializer.string();
		RedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

		setKeySerializer(stringSerializer);
		setValueSerializer(stringSerializer);
		setHashKeySerializer(stringSerializer);
		setHashValueSerializer(serializer);
		setConnectionFactory(connectionFactory);
		afterPropertiesSet();
	}

}
