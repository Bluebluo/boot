package com.echo.outqry.web.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: outqry
 * @description: 验证码生成器
 * @author: Li Hongmou
 * @create: 2019-01-24 10:31
 **/
public interface ValidateCodeGenerator {

    ValidateCode generator(ServletWebRequest request);
}
