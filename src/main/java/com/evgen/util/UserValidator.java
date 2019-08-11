package com.evgen.util;

import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public boolean checkEmailExistence(String email) {

        System.out.println("in Validator - user mail:" + email + "$");
        if (userService.getByEmail(email) != null) {
            return true;
        }
        return false;
    }
}
