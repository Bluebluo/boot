package com.echo.outqry.entity;

import com.echo.outqry.entity.constraint.MyConstraint;

import javax.validation.constraints.NotBlank;

public class UserInfo {

    private String uId;

    @NotBlank
    private String userName;

    private String passWord;

    @MyConstraint(message = "姓名不能为空")
    private String realName;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
