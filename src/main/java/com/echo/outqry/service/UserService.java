package com.echo.outqry.service;

import com.echo.outqry.entity.UserInfo;

import java.util.List;

public interface UserService {

    public boolean authenticate(String userName, String passWord);

    public UserInfo getUserInfoByName(String userName);

    public List<String> getRolesList(String userName);

    public void greeting(String something);
}
