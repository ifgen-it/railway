package com.evgen.controller;

import com.evgen.dto.station.RoutePathDTO;
import com.evgen.service.StationService;
import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

        System.out.println("----> Get Mapping timetable");
        model.addAttribute("stations", stationService.getAllStations());

        return "/timetable";
    }

    @PostMapping("/timetable")
    public String timetable(@RequestParam(name = "stationId") int stationId,
                            Model model) {

        model.addAttribute("stations", stationService.getAllStations());

        System.out.println("----> Post Mapping timetable");
        model.addAttribute("arrivals", stationService.getArrivals(stationId));
        model.addAttribute("departures", stationService.getDepartures(stationId));

        model.addAttribute("stationName", stationService.getStation(stationId).getStationName());

        return "/timetable";
    }

}
