package com.echo.outqry.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @RequestMapping("/api/test")
    public String test(){
        System.out.println("13");
        return "test";
    }
}
