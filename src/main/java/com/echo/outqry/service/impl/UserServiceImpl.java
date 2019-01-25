package com.echo.outqry.service.impl;

import com.echo.outqry.entity.UserInfo;
import com.echo.outqry.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean authenticate(String userName, String passWord) {
        return true;
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        UserInfo user = new UserInfo();
        user.setuId("1");
        user.setUserName("lhm");
        return user;
    }

    @Override
    public List<String> getRolesList(String userName) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return roles;
    }

    @Override
    public void greeting(String something) {
        System.out.println(something);
    }
}
