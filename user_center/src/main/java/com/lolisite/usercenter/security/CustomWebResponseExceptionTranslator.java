package com.lolisite.usercenter.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/**
 * @author cap_ljf
 * @description 自定义token授权异常处理器
 * @create 2020/04/11 15:50
 */
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        return super.translate(e);
    }
}
