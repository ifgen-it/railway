package com.evgen.util;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    private UserDAO userDAO;

    public boolean checkEmailExistence(User user) {

        if (userDAO.getOne(user.getEmail()) != null) {
            return true;
        }
        return false;
    }
}
