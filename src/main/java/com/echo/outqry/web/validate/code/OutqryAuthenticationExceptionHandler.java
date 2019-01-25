package com.echo.outqry.web.validate.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: outqry
 * @description: 登录失败异常
 * @author: Li Hongmou
 * @create: 2019-01-24 15:17
 **/
@Component
public class OutqryAuthenticationExceptionHandler extends SimpleUrlAuthenticationFailureHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        super.onAuthenticationFailure(request, response, exception);
    }
}
