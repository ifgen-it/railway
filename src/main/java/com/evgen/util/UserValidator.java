package com.evgen.util;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator {

    @Autowired
    private UserDAO userDAO;

    public String checkEmailExistence(User user) {
        String emailError = "";
        if (userDAO.getOne(user.getEmail()) != null) {
            emailError = "This email is already in use";
        }
        return emailError;
    }
}
