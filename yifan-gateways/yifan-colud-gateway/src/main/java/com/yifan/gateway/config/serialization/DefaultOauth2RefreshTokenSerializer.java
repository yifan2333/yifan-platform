package com.yifan.gateway.config.serialization;

import java.lang.reflect.Type;

import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class DefaultOauth2RefreshTokenSerializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        if (type instanceof DefaultOAuth2RefreshToken) {
            JSONObject jsonObject = parser.parseObject();
            String tokenId = jsonObject.getString("value");
            DefaultOAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(tokenId);
            return (T) refreshToken;
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}

