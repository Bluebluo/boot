package com.echo.outqry.config;

import com.echo.outqry.web.filter.TimeFilter;
import com.echo.outqry.web.interceptor.MethodInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Bean
    public FilterRegistrationBean filterRegistration(){
        TimeFilter timeFilter = new TimeFilter();
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(timeFilter);

        filterRegistration.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistration;
    }

    @Autowired
    private MethodInterceptor interceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
