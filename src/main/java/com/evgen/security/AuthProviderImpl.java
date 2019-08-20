package com.evgen.security;

import com.evgen.dto.user.UserDTO;
import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        UserDTO userDTO = userService.getByEmail(email);
        if (userDTO == null){

            System.out.println("---> User not found");
            throw new UsernameNotFoundException("User not found");
        }

        String password = authentication.getCredentials().toString();
        if (!password.equals(userDTO.getPassword())){

            System.out.println("---> Bad credentials");
            throw new BadCredentialsException("Bad credentials");
        }

        // NOW WE CAN GIVE A ROLE TO USER

        List<GrantedAuthority> authorities = new ArrayList<>(); //  LIST WITH ROLES

        // NEED TO USE THIS LIST THEN

        return new UsernamePasswordAuthenticationToken(userDTO, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
