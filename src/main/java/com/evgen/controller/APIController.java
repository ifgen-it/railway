package com.evgen.controller;

import com.evgen.dto.station.StationDTO;
import com.evgen.service.MessageService;
import com.evgen.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class APIController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private StationService stationService;


    @GetMapping("/api/stations")
    @ResponseBody
    public List<String> getStations(){

        List<String> stationNames = new ArrayList<>();
        List<StationDTO> stations = stationService.getAllStations();

        stations.forEach(station -> stationNames.add(station.getStationName()));

        String strStations = "";
        for(String stationName : stationNames){
            strStations += stationName + ", ";
        }
        System.out.println("---> API - stations: " + strStations);

        return stationNames;
    }

    @GetMapping("/api")
    public String getApi(Model model) {

        System.out.println("----> Get Mapping API");

        return "/api";
    }


}
