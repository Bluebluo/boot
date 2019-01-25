package com.echo.outqry.security;

public class IamAuthenticationException extends org.springframework.security.core.AuthenticationException {

    public IamAuthenticationException(String msg){
        super(msg);
    }

}
