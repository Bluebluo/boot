package com.echo.outqry.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * authenticationProvider由authenticationManager管理，实际进行校验的地方
 */
@Component
public class IamAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IamAuthenticationService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IamAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(authService.authenticate(userName, password)){
            return authService.getAuthenticationToken(userName);
        }else{
            throw new IamAuthenticationException("Authentication failed for user " + userName);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
