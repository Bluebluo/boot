package com.echo.outqry.web.validate.code.sms;

import com.echo.outqry.web.validate.code.ValidateCode;
import com.echo.outqry.web.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * @program: outqry
 * @description: 短信验证码生成器
 * @author: Li Hongmou
 * @create: 2019-01-24 10:30
 **/
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomAlphanumeric(6);
        return new ValidateCode(code, 120);
    }
}
