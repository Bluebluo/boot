package com.echo.outqry.web.validate.code;

import com.echo.outqry.web.controller.FileController;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @program: outqry
 * @description:
 * @author: Li Hongmou
 * @create: 2019-01-21 18:04
 **/
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.getParamOnValidate());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new RuntimeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
