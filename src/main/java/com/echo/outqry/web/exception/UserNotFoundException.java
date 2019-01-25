package com.echo.outqry.web.exception;

public class UserNotFoundException extends RuntimeException{

    private String something;

    public UserNotFoundException(String something) {
        super("this is user not found exception");
        this.something = something;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }
}
