package com.evgen.controller;

import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.service.StationService;
import com.evgen.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private StationService stationService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/journey")
    public String getJourney(Model model) {

        model.addAttribute("stations", stationService.getAllStations());

        return "/journey";
    }

    @PostMapping("/journey")
    public String journey(@RequestParam(name = "beginStation") int beginStationId,
                          @RequestParam(name = "endStation") int endStationId,
                          Model model) {

        // CHECKING FOR ERRORS
        String beginStationError = "";
        String endStationError = "";
        boolean error = false;

        System.out.println("===== in the Journey Post =======");
        System.out.println("----- begin station id = " + beginStationId);
        System.out.println("----- end station id = " + endStationId);

        if (beginStationId == 0 || endStationId == 0) {

            if (beginStationId == 0){
                beginStationError = "Select begin station";
                error = true;
            }
            if (endStationId == 0){
                endStationError = "Select end station";
                error = true;
            }

        } else {
            if (beginStationId == endStationId) {
                endStationError = "Begin and end stations must be different";
                error = true;
            }
        }

        if (error == true) {
            model.addAttribute("beginStationError", beginStationError);
            model.addAttribute("endStationError", endStationError);
            model.addAttribute("stations", stationService.getAllStations());
            return "/journey";
        }

        model.addAttribute("stations", stationService.getAllStations());

        List<Integer> routesId = stationService.getCommonRoutes(beginStationId, endStationId);

        System.out.println("======== in the Post journey ===========");
        System.out.println("-----> Common routes = " + routesId);

        List<RouteExtDTO> routesExt = new ArrayList<>();
        routesId.forEach(id -> routesExt.add(stationService.getRouteExt(id)));

        model.addAttribute("directRoutes", routesExt);
        model.addAttribute("directRoutesNum", routesExt.size());
        model.addAttribute("beginStationName", stationService.getStation(beginStationId).getStationName());
        model.addAttribute("endStationName", stationService.getStation(endStationId).getStationName());

        return "/journey";
    }

}
