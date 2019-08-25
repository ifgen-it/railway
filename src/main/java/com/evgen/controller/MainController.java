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

        /*
        System.out.println("---> principal = " + principal);
        System.out.println("---> model = " + model);

        Authentication authentication = (Authentication)principal;
        System.out.println("===> authentication = " + authentication);
        if (authentication == null){
            model.addAttribute("user", "Guest");
        } else {
            System.out.println("---> getIndex: authentication: " + authentication.getPrincipal());
            UserDTO user = (UserDTO)authentication.getPrincipal();

            model.addAttribute("user", user.getFirstName());
        }
    */

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

//        if (5 > 4){
//            throw new NoHandlerFoundException("GET", "/info", new HttpHeaders());
//        }

        return "/info";
    }

    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name,
            Model model){
        model.addAttribute("title",
                "UserEntity railway traffic information system");
        model.addAttribute("msg", "Hello, " + name + "! Wanna buy ticket?");
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw(){
        String str = "title: Passenger railway traffic information system";
        return str;
    }




}
