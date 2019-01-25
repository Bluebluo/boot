package com.echo.outqry.entity.constraint.validator;

import com.echo.outqry.entity.constraint.MyConstraint;
import com.echo.outqry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MyConstraintVlidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("constraint init ");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        userService.greeting("tom");
        return false;
    }

}
