package com.echo.outqry.web.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import com.echo.outqry.web.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> HandleUserNotFoundException(UserNotFoundException ex){
        Map<String,Object> map = new HashMap<>();
        map.put("something",ex.getSomething());
        map.put("msg",ex.getMessage());
        return map;
    }
}
