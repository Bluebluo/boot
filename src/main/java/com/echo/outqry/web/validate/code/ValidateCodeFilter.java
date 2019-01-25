package com.echo.outqry.web.validate.code;

import com.echo.outqry.properties.SecurityConstants;
import com.echo.outqry.web.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: outqry
 * @description: 过滤器，校验验证码
 * @author: Li Hongmou
 * @create: 2019-01-22 10:22
 **/
@Component()
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeProcessorHolder processorHolder;

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_URL, ValidateCodeType.IMAGE);
        addUrlToMap("/api/login", ValidateCodeType.IMAGE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            try{
                processorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request,response));
            }catch (ValidateCodeException ex){
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
                return;
            }
            logger.info("validateType = "+type);
        }
        filterChain.doFilter(request, response);
    }

    private void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, "");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> keys = urlMap.keySet();
            for (String url : keys) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    return urlMap.get(url);
                }
            }
        }
        return null;
    }
}
