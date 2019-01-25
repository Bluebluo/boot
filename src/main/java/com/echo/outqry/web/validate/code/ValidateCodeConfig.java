package com.echo.outqry.web.validate.code;

import com.echo.outqry.properties.SecurityConstants;
import com.echo.outqry.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * @program: outqry
 * @description: 配置文件
 * @author: Li Hongmou
 * @create: 2019-01-22 11:51
 **/
@Component("validateCodeConfig")
public class ValidateCodeConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private Filter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, JwtAuthenticationFilter.class);
    }
}
