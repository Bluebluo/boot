package com.echo.outqry.security;

import com.echo.outqry.entity.UserInfo;
import com.echo.outqry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IamAuthenticationService {

    @Autowired
    private UserService userService;

    public boolean authenticate(String userName, String passWord) throws AuthenticationException {
        if(userService.authenticate(userName, passWord)){
            return true;
        }else{
            throw new IamAuthenticationException("Authentication failed for user " + userName);
        }
    }

    @Cacheable(cacheNames = {"userToken"})
    public UsernamePasswordAuthenticationToken getAuthenticationToken(String userName){
        UserInfo userInfo = userService.getUserInfoByName(userName);
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> rolesList = userService.getRolesList(userName);
        for (String role: rolesList) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(userInfo, null, roles);
    }

}
