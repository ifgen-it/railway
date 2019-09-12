package com.evgen.controller;


import com.evgen.dto.user.UserDTO;
import com.evgen.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    private SecurityService securityService;


    @GetMapping("/")
    public String getIndex(Principal principal, Model model){


        UserDTO user = securityService.getAuthUser();
        if (user == null){
            model.addAttribute("user", "Guest");
        }
        else {
            model.addAttribute("user", user.getFirstName());
        }

        return "/index";
    }

    @GetMapping("/info")
    public String getInfo(){

        return "/info";
    }




}
