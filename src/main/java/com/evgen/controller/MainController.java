package com.evgen.controller;


import com.evgen.dto.user.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class MainController {



    @GetMapping("/")
    public String getIndex(Principal principal, Model model){

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


        return "/index";
    }

    @GetMapping("/info")
    public String getInfo(){
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
