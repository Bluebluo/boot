package com.echo.outqry.web.validate.code;

import com.echo.outqry.properties.SecurityConstants;

public enum ValidateCodeType {

    IMAGE {
        @Override
        public String getParamOnValidate(){
            return SecurityConstants.DEFAULT_VALIDATE_CODE_IMAGE;
        }
    },
    SMS {
        @Override
        public String getParamOnValidate(){
            return SecurityConstants.DEFAULT_VALIDATE_CODE_SMS;
        }
    };


    public abstract String getParamOnValidate();
}
