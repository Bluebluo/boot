package com.echo.outqry.web.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @program: outqry
 * @description: 验证码异常类型
 * @author: Li Hongmou
 * @create: 2019-01-22 15:51
 **/
public class ValidateCodeException extends AuthenticationException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ValidateCodeException(String message) {
        super(message);
    }
}
