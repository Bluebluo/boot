package com.echo.outqry.web.controller;

import com.echo.outqry.web.validate.code.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/validate")
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder processorHolder;

    @GetMapping("/{type}")
    public void getImageCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        processorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }
}
