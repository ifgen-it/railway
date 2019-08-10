package com.evgen.controller;

import com.evgen.service.StationService;
import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/stations")
    public String getStations(Model model) {

        model.addAttribute("stations", stationService.getAllStations());
        return "/stations";
    }

    @GetMapping("/routes")
    public String getRoutes(Model model) {

        model.addAttribute("routes", stationService.getAllRoutes());
        return "/routes";
    }

    @GetMapping("/timetable")
    public String getTimetable(Model model) {

        model.addAttribute("stations", stationService.getAllStations());

        model.addAttribute("arrivals", stationService.getArrivals(1));

        return "/timetable";
    }


}
