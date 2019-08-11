package com.evgen.controller;

import com.evgen.dto.station.ArcDTO;
import com.evgen.dto.station.RoutePathDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StationController {

    @Autowired
    private StationService stationService;


    @GetMapping("/arcs")
    public String getArcs(Model model) {

        model.addAttribute("arcs", stationService.getAllArcs());
        model.addAttribute("stations", stationService.getAllStations());

        return "/arcs";
    }

    @PostMapping("/arcs")
    public String arcs(@RequestParam(name = "beginStation") int beginStationId,
                       @RequestParam(name = "endStation") int endStationId,
                       @RequestParam(name = "length") int length,
                       Model model) {

        // CHECKING FOR ERRORS
        String beginStationError = "";
        String endStationError = "";
        String lengthError = "";
        boolean error = false;

        if (length < 1 || length > 4000) {
            lengthError = "Input real length";
            error = true;
        }

        if (beginStationId == endStationId) {
            endStationError = "Begin and end stations must be different";
            error = true;
        }

        // CHECKING FOR THIS ARC EXISTS
        if (beginStationId != endStationId) {

            // CHECKING FOR THIS ARC EXISTS
            if (stationService.getArcByStations(beginStationId, endStationId) != null) {

                beginStationError = "This arc already in use";
                error = true;
            }
            // CHECKING FOR EQUAL LENGTH FOR REVERSE ARC
            else {

                ArcDTO reverseArc = stationService.getArcByStations(endStationId, beginStationId);
                if (reverseArc != null && reverseArc.getLength() != length) {
                    lengthError = "Length of this arc must be equal reverse arc, i.e. " +
                            reverseArc.getLength() + " km";
                    error = true;
                }
            }

        }


        if (error == true) {
            model.addAttribute("beginStationError", beginStationError);
            model.addAttribute("endStationError", endStationError);
            model.addAttribute("lengthError", lengthError);

            model.addAttribute("stations", stationService.getAllStations());
            model.addAttribute("arcs", stationService.getAllArcs());
            return "/arcs";
        }

        // ADD ARC
        StationDTO beginStationDTO = stationService.getStation(beginStationId);
        StationDTO endStationDTO = stationService.getStation(endStationId);

        ArcDTO arcDTO = new ArcDTO(beginStationDTO, endStationDTO, length);
        stationService.addArc(arcDTO);

        System.out.println("------- Arc must be added --------------");

        // ALL RIGHT
        model.addAttribute("arcs", stationService.getAllArcs());
        model.addAttribute("stations", stationService.getAllStations());

        return "/arcs";
    }

    @GetMapping("/stations")
    public String getStations(Model model) {

        model.addAttribute("stations", stationService.getAllStations());
        return "/stations";
    }

    @PostMapping("/stations")
    public String stations(@RequestParam(name = "stationName") String stationName,
                           Model model) {

        System.out.println("======= in the Post mapping Stations =======");

        // CHECKING FOR ERRORS
        String stationNameError = "";
        boolean error = false;

        if (stationService.getByName(stationName) != null) {
            stationNameError = "This station name already in use";
            error = true;
        }

        if (error == true) {
            model.addAttribute("stationNameError", stationNameError);
            model.addAttribute("stations", stationService.getAllStations());

            return "/stations";
        }

        // ADD STATION
        StationDTO stationDTO = new StationDTO();
        stationDTO.setStationName(stationName);
        stationService.addStation(stationDTO);
        System.out.println("------- station must be added --------------");

        model.addAttribute("stations", stationService.getAllStations());

        return "/stations";
    }

    @GetMapping("/routes")
    public String getRoutes(Model model) {

       // model.addAttribute("routes", stationService.getAllRoutes());

        model.addAttribute("routes", stationService.getAllRoutesExt());


        System.out.println("====== > GETTING route Path with routeId : 2 ");
        RoutePathDTO rPath = stationService.getFirstArc(2);
        System.out.println("====== > route Path with routeId : 2 = " + rPath);
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
