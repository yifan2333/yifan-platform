package com.yifan.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * The type Authorization server config.
 *
 * @author wuyifan
 * @date 2019年12月30日 14:58
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * The Authentication manager.
     */
    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * The Token store.
     */
    @Resource
    private TokenStore tokenStore;

    @Resource
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        final JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
//        // 导入证书
//        KeyStoreKeyFactory keyStoreKeyFactory =
//                new KeyStoreKeyFactory(new ClassPathResource("yifan.keystore"), "123456".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("yifan"));
//        return converter;
//    }

    /**
     * Configure.
     * 用来配置客户端详情服务，
     * 客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。客户端就是指第三方应用
     * 配置客户端的 service，也就是应用怎么获取到客户端的信息
     * ，一般来说是从内存或者数据库中获取，已经提供了他们的默认实现，你也可以自定义
     * @param clients the clients
     * @throws Exception the exception
     * @author wuyifan
     * @date 2019年12月30日 14:58
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    /**
     * Configure.
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * 配置授权服务器各个端点的非安全功能，
     * 如令牌存储，令牌自定义，用户批准和授权类型。
     * 如果需要密码授权模式，需要提供 AuthenticationManager 的 bean。
     * @param endpoints the endpoints
     * @author wuyifan
     * @date 2019年12月30日 14:58
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
//                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(tokenStore);
    }

    /**
     * Configure.
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * 配置授权服务器的安全信息，比如 ssl 配置、
     * checktoken 是否允许访问，
     * 是否允许客户端的表单身份验证等
     * @param security the security
     * @author wuyifan
     * @date 2019年12月30日 14:58
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

}