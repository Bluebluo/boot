package com.echo.outqry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index.html","/favicon.ico","/js/**","/css/**","/img/**","/fonts/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                .anyRequest().authenticated();
    }
}
