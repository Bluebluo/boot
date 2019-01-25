package com.echo.outqry.web.controller;

import com.echo.outqry.entity.LoginResponse;
import com.echo.outqry.entity.UserInfo;
import com.echo.outqry.web.exception.UserNotFoundException;
import com.echo.outqry.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestParam(name = "username")String username,
                                    @RequestParam(name = "password")String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        List<String> roles = authentication.getAuthorities().stream().map(authority ->
                ((GrantedAuthority)authority).getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(token, roles));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> userLogout(@RequestParam(name = "token",required = false) String token){

       return null;
    }

    @PostMapping("/getUserModel")
    public String getUserModel(@Valid @RequestBody UserInfo user, BindingResult errors){
        StringBuffer errMsg = new StringBuffer();
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(err -> {
                errMsg.append(err.getDefaultMessage());
            });
            throw new UserNotFoundException(errMsg.toString());
        }
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(user.getUserName());
        return user.getUserName();
    }
}
