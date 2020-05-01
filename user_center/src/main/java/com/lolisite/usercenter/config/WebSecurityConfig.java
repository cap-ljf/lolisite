package com.lolisite.usercenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author cap_ljf
 * @description 安全配置
 * @create 2020/04/07 09:55
 */
@EnableWebSecurity //启用Spring Security的Web安全支持
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    /**
     * 定义了哪些URL路径应该被保护，哪些不应该
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/home", "/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);
    }

}
