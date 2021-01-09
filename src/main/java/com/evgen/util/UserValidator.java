package com.evgen.util;

import com.evgen.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private static final Logger logger = Logger.getLogger(UserValidator.class);

    @Autowired
    @Qualifier("userServiceRestClient")
    private UserService userService;

    public boolean checkEmailExistence(String email) {

        logger.info("In Validator - user mail:" + email);
        if (userService.getByEmail(email) != null) {
            return true;
        }
        return false;
    }
}
