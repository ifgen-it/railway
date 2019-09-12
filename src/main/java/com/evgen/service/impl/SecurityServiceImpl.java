package com.evgen.service.impl;

import com.evgen.dto.user.UserDTO;
import com.evgen.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    @Override
    public UserDTO getAuthUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Authentication = " + authentication);
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            logger.info("Authentication.getPrincipal: " + authentication.getPrincipal());
            UserDTO user = (UserDTO) authentication.getPrincipal();
            return user;
        }
    }



}
