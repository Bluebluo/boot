package com.echo.outqry.web.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: outqry
 * @description: 校验码处理器，封装不同校验码的处理逻辑
 * @author: Li Hongmou
 * @create: 2019-01-21 16:44
 **/
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX_= "SESSION_KEY_FOR_CODE_";

    /** 
    * @Description: 生成验证码
    * @Param: [request] 
    * @return: void 
    * @Author: Li HongMou
    * @Date: 2019/1/21 
    */ 
    void create(ServletWebRequest request) throws Exception;

    /**
    * @Description: 校验验证码
    * @Param: [code, request]
    * @return: boolean
    * @Author: Li HongMou
    * @Date: 2019/1/21
    */
    boolean validate(ServletWebRequest request);
}
