package com.echo.outqry.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class MethodInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(MethodInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("method interceptor pre,拦截器可以获取到所有请求调用的类和方法，包括spring本身的");
        request.setAttribute("start",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("method interceptor start");
        logger.info(((HandlerMethod)handler).getBean().getClass().getName(),
                ((HandlerMethod)handler).getMethod().getName());
        logger.info("method interceptor finish");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long start = (long)request.getAttribute("start");
        logger.info("method interceptor end,无论请求成功失败都会进到这里来 spend time" + (new Date().getTime() - start));
    }
}
