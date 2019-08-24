package com.evgen.service.impl;

import com.evgen.dto.user.UserDTO;
import com.evgen.service.SecurityService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {


    @Override
    public UserDTO getAuthUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("===> authentication = " + authentication);
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            //System.out.println("---> getIndex: authentication: " + authentication.getPrincipal());
            UserDTO user = (UserDTO) authentication.getPrincipal();
            return user;
        }
    }



}
