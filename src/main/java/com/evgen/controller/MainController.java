package com.evgen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {



    @GetMapping("/")
    public String getIndex(){
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
