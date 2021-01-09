package com.evgen.controller;

import com.evgen.converter.StationConverter;
import com.evgen.dto.station.RoutePathDTO;
import com.evgen.dto.station.RoutePathSimpleDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.station.StationSimpleDTO;
import com.evgen.service.MessageService;
import com.evgen.service.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class APIController {

    private static final Logger logger = Logger.getLogger(APIController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private StationService stationService;

    @Autowired
    private StationConverter stationConverter;

    @GetMapping("/api")
    public String getApi(Model model) {

        return "/api";
    }

    @GetMapping("/api/stations")
    @ResponseBody
    public List<StationSimpleDTO> getStations() {

        List<StationSimpleDTO> stationsSimple = new ArrayList<>();
        List<StationDTO> stations = stationService.getAllStations();
        for (StationDTO station : stations) {

            StationSimpleDTO stationSimple = stationConverter.convertStationToSimple(station);
            stationsSimple.add(stationSimple);
        }
        logger.info("API - stations simple: " + stationsSimple);
        return stationsSimple;
    }

    @GetMapping("/api/arrivals/{stationId}")
    @ResponseBody
    public List<RoutePathSimpleDTO> getArrivals(
            @PathVariable(name = "stationId") String strStationId) {

        logger.info("API - get Arrivals, station id = " + strStationId);

        int stationId = 0;
        try {
            stationId = Integer.parseInt(strStationId);
        } catch (NumberFormatException e) {
            return null;
        }
        StationDTO station = stationService.getStation(stationId);
        if (station == null) {
            return null;
        }

        List<RoutePathDTO> routePaths = stationService.getArrivals(station.getStationId());
        List<RoutePathSimpleDTO> routePathsSimple = new ArrayList<>();

        for (RoutePathDTO routePath : routePaths) {

            RoutePathSimpleDTO routePathSimple = stationConverter.convertRoutePathToSimple(routePath, true);
            routePathsSimple.add(routePathSimple);
        }

        return routePathsSimple;
    }

    @GetMapping("/api/departures/{stationId}")
    @ResponseBody
    public List<RoutePathSimpleDTO> getDepartures(
            @PathVariable(name = "stationId") String strStationId) {

        logger.info("API - get Departures, station id = " + strStationId);

        int stationId = 0;
        try {
            stationId = Integer.parseInt(strStationId);
        } catch (NumberFormatException e) {
            return null;
        }
        StationDTO station = stationService.getStation(stationId);
        if (station == null) {
            return null;
        }

        List<RoutePathDTO> routePaths = stationService.getDepartures(station.getStationId());
        List<RoutePathSimpleDTO> routePathsSimple = new ArrayList<>();

        for (RoutePathDTO routePath : routePaths) {

            RoutePathSimpleDTO routePathSimple = stationConverter.convertRoutePathToSimple(routePath, false);
            routePathsSimple.add(routePathSimple);
        }

        return routePathsSimple;


    }


}
