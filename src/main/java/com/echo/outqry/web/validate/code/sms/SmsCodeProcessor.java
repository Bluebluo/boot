package com.echo.outqry.web.validate.code.sms;

import com.echo.outqry.web.validate.code.ValidateCode;
import com.echo.outqry.web.validate.code.ValidateCodeProcessor;
import com.echo.outqry.web.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: outqry
 * @description: create and send
 * @author: Li Hongmou
 * @create: 2019-01-24 11:56
 **/
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        System.out.println("手机验证码是" + validateCode.getCode());
    }
}
