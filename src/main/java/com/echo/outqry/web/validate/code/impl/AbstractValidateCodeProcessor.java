package com.echo.outqry.web.validate.code.impl;

import com.echo.outqry.web.validate.code.*;
import com.echo.outqry.web.validate.code.image.ImageCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: outqry
 * @description: 验证码处理器
 * @author: Li Hongmou
 * @create: 2019-01-21 16:55
 **/
public class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy session = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    /**
     * @param request
     * @Description: 生成验证码
     * @Param: [request]
     * @return: void
     * @Author: Li HongMou
     * @Date: 2019/1/21
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * @param request
     * @Description: 校验验证码
     * @Param: [code, request]
     * @return: boolean
     * @Author: Li HongMou
     * @Date: 2019/1/21
     */
    @Override
    public boolean validate(ServletWebRequest request) {
        ValidateCodeType validateType = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);
        C codeInSession = (C) session.getAttribute(request, sessionKey);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "validateCode");
        } catch (ServletRequestBindingException ex) {
            throw new ValidateCodeException(validateType + "服务器错误");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(validateType + "请输入验证码");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException(validateType + "服务器未发现验证码");
        }
        if (codeInSession.isExpired()) {
            throw new ValidateCodeException(validateType + "验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(validateType + "验证码不正确");
        }
        return true;
    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * @Description: 生成获取验证码
     * @Param: [request]
     * @return: C
     * @Author: Lihm001
     * @Date: 2019/1/21
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).getParamOnValidate();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator generator = validateCodeGeneratorMap.get(generatorName);
        if(generator == null){
            throw new ValidateCodeException("不存在的验证码生成器");
        }
        return (C) generator.generator(request);
    }

    /**
     * @Description: 保存到session
     * @Param: [validateCode]
     * @return: void
     * @Author: Li HongMou
     * @Date: 2019/1/21
     */
    private void save(ServletWebRequest request, C validateCode) {
        session.setAttribute(request, getSessionKey(request), validateCode);
    }

    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX_ + getValidateCodeType(request);
    }

    public void send(ServletWebRequest request, C validateCode) throws Exception {
    }
}
