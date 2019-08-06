package com.evgen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationController {

    @GetMapping("/stations")
    public String getStations(Model model) {

//        model.addAttribute("stations", userService.getAll());

        return "/stations";
    }
}
