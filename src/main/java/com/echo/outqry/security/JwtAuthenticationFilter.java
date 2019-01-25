package com.echo.outqry.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

    public static final String TOKEN_HEADER = "Bearer ";
    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IamAuthenticationService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            String authToken = getToken(httpServletRequest);
            if(StringUtils.hasText(authToken) && jwtTokenProvider.validateToken(authToken)){
                String userName = jwtTokenProvider.getUserNameFromJwt(authToken);
                UsernamePasswordAuthenticationToken authentication = authService.getAuthenticationToken(userName);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception ex){
            LOGGER.error("could not set user authentication in security context ", ex);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public String getToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHENTICATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_HEADER)){
            return bearerToken.substring(TOKEN_HEADER.length());
        }
        return null;
    }
}
