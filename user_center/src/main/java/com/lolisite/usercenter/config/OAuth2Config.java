package com.lolisite.usercenter.config;

import com.lolisite.usercenter.security.CustomWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author cap_ljf
 * @description oauth 2.0 认证服务器配置
 * @create 2020/04/09 22:49
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    /**
     * 默认数据源db
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 从db中查找client
     *
     * @return JdbcClientDetailsService
     */
    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * token持久化bean
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 授权服务器 tokenService，负责token的生成、刷新、获取方法
     *
     * @return AuthorizationServerTokenServices
     */
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore()); //设置自定义的持久化工具
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setTokenEnhancer(accessTokenConverter());
        return tokenServices;
    }

    /**
     * 负责转换 jwt编码的token <> oauth authentication信息
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("secret");
        return converter;
    }

    /**
     * 授权失败处理器
     *
     * @return WebResponseExceptionTranslator
     */
    @Bean
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }


    /**
     * Spring Security OAuth 暴露了两个端点 (/auth/token_key 和 /auth/check_token)，这两个端点默认是关闭的（denyAll()）
     * 下面通过配置permitAll()和isAuthenticated()这两个方法修改了这两个端口的校验方式。
     * 详见：AuthorizationServerSecurityConfiguration.java:90
     *
     * @param security 配置
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

        /**
     * 通过jdbcClient查询用户信息
     *
     * @param clients clientDetail安全配置
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 授权服务器端点配置
     *
     * @param endpoints 授权服务器配置
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //如果需要使用password模式认证，必须手动注入authenticationManager，参考org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer.getDefaultTokenGranters
                .tokenStore(tokenStore()) //token存储器
                .tokenServices(authorizationServerTokenServices()) //tokenService，生成、刷新、获取token
                .accessTokenConverter(accessTokenConverter()) //token转换器，主要用于加密
                .exceptionTranslator(webResponseExceptionTranslator()); //授权失败处理器
    }
}
