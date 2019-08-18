package com.evgen.controller;

import com.evgen.dto.station.ArcDTO;
import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.RoutePathDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.train.TrainDTO;
import com.evgen.service.StationService;
import com.evgen.service.TrainService;
import com.evgen.service.impl.UseReservedTrainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;


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


        //System.out.println("====== > GETTING route Path with routeId : 2 ");
        //RoutePathDTO rPath = stationService.getFirstArc(2);
        //System.out.println("====== > route Path with routeId : 2 = " + rPath);
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

    @GetMapping("/routes/new/arcs")
    public String getNewRoute(Model model,
                              HttpSession session) {

        System.out.println("----> Get Mapping New Route");

        // GET LIST OF AVAILABLE ARCS
        List<ArcDTO> arcs = null;

        if (session.getAttribute("tempRoutePaths") == null) {
            arcs = stationService.getAllArcs();
        } else {
            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");
            RoutePathDTO lastRoutePath = tempRoutePaths.get(tempRoutePaths.size() - 1);

            StationDTO endStation = lastRoutePath.getArc().getEndStation();
            List<ArcDTO> outArcs = stationService.getOutArcs(endStation.getStationId());

            arcs = outArcs;
        }

        model.addAttribute("arcs", arcs);
        return "/new_route";
    }

    @PostMapping("/routes/new/arcs")
    public String newRoute(@RequestParam(name = "arcId") int arcId,
                           @RequestParam(name = "departureTime") String strDepartureTime,
                           @RequestParam(name = "arrivalTime") String strArrivalTime,
                           Model model,
                           HttpSession session) {

        System.out.println("----> Post Mapping New Route");
        System.out.println("----> arcId = " + arcId);
        System.out.println("----> str departure time = " + strDepartureTime);
        System.out.println("----> str arrival time = " + strArrivalTime);


        // GET LIST OF AVAILABLE ARCS - THIS ARCS WILL BE USED ONLY FOR CASE WITH DATETIME ERROR
        List<ArcDTO> arcs = null;

        if (session.getAttribute("tempRoutePaths") == null) {
            arcs = stationService.getAllArcs();
        } else {
            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");
            RoutePathDTO lastRoutePath = tempRoutePaths.get(tempRoutePaths.size() - 1);

            System.out.println("----> Last arc: " + lastRoutePath);
            StationDTO beginStation = lastRoutePath.getArc().getBeginStation();
            StationDTO endStation = lastRoutePath.getArc().getEndStation();

            List<ArcDTO> outArcs = stationService.getOutArcs(endStation.getStationId());
            System.out.println("---> Out arcs of station " + endStation.getStationName() + " : " + outArcs);

            // HERE WE CAN REMOVE BEGIN STATION FROM OUT ARCS  ?? FOR NO-CYCLE LOOPS

            arcs = outArcs;

        }

        // CONVERTING DATES

        strDepartureTime = strDepartureTime.replace('T', ' ');
        strArrivalTime = strArrivalTime.replace('T', ' ');

        System.out.println("After replace:");
        System.out.println("----> str departure time = " + strDepartureTime);
        System.out.println("----> str arrival time = " + strArrivalTime);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime departureTime = LocalDateTime.parse(strDepartureTime, dtf);
        LocalDateTime arrivalTime = LocalDateTime.parse(strArrivalTime, dtf);

        System.out.println("----> departure time = " + departureTime);
        System.out.println("----> arrival time = " + arrivalTime);

        // VALIDATE DATE TIME

        String departureTimeError = "";
        String arrivalTimeError = "";
        boolean dateTimeError = false;

        // DEPARTURE TIME
        if (session.getAttribute("tempRoutePaths") == null) {

            if (departureTime.isBefore(LocalDateTime.now())) {

                System.out.println("--> departureTimeError - Departure time must be after now time");
                departureTimeError = "Departure time must be after now time";
                dateTimeError = true;
            }
        } else {

            // GET ARRIVAL TIME LAST ARC
            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");
            RoutePathDTO lastRoutePath = tempRoutePaths.get(tempRoutePaths.size() - 1);
            LocalDateTime lastArcArrivalTime = lastRoutePath.getArrivalTime();

            if (departureTime.isBefore(lastArcArrivalTime)) {

                System.out.println("--> departureTimeError - Departure time must be after previous arc arrival time");
                departureTimeError = "Departure time must be after previous arc arrival time";
                dateTimeError = true;
            }
        }

        // ARRIVAL TIME
        if (departureTime.isAfter(arrivalTime)) {

            System.out.println("--> arrivalTimeError - Arrival time must be after departure time");
            arrivalTimeError = "Arrival time must be after departure time";
            dateTimeError = true;
        }

        if (dateTimeError == true) {
            model.addAttribute("departureTimeError", departureTimeError);
            model.addAttribute("arrivalTimeError", arrivalTimeError);
            model.addAttribute("arcs", arcs);

            return "/new_route";
        }


        // ALL RIGHT - CONTINUE

        RoutePathDTO routePath = new RoutePathDTO();
        ArcDTO newArc = stationService.getArc(arcId);

        routePath.setArc(newArc);
        routePath.setDepartureTime(departureTime);
        routePath.setArrivalTime(arrivalTime);

        if (session.getAttribute("firstArcAdded") == null) {

            // NEED TO ADD LIST OF ROUTE PATHS INTO SESSION
            List<RoutePathDTO> tempRoutePaths = new ArrayList<>();
            tempRoutePaths.add(routePath);
            session.setAttribute("firstArcAdded", true);
            session.setAttribute("tempRoutePaths", tempRoutePaths);

        } else {
            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");
            tempRoutePaths.add(routePath);
            session.setAttribute("tempRoutePaths", tempRoutePaths);
        }

        // UPDATE OUT ARCS
        StationDTO beginStation = newArc.getBeginStation();
        StationDTO endStation = newArc.getEndStation();

        List<ArcDTO> outArcs = stationService.getOutArcs(endStation.getStationId());
        System.out.println("----> out Arcs: in Post : " + outArcs);
        // HERE WE CAN REMOVE BEGIN STATION FROM OUT ARCS  ?? FOR NO-CYCLE LOOPS

        arcs = outArcs;

        model.addAttribute("arcs", arcs);

        return "/new_route";
    }


    @GetMapping("/routes/new/train")
    public String getAttachTrain(Model model,
                                 HttpSession session) {

        System.out.println("----> Get Mapping Attach train");

        // GET FREE TRAINS
        List<TrainDTO> freeTrains = null;

        if (session.getAttribute("tempRoutePaths") == null) {

            return "redirect:/routes/new/arcs";

        } else {

            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");
            RoutePathDTO firstRoutePath = tempRoutePaths.get(0);
            RoutePathDTO lastRoutePath = tempRoutePaths.get(tempRoutePaths.size() - 1);

            LocalDateTime departureTime = firstRoutePath.getDepartureTime();
            LocalDateTime arrivalTime = lastRoutePath.getArrivalTime();

            List<Integer> trainsId = trainService.getFreeTrains(departureTime, arrivalTime);
            System.out.println("----> Departure time = " + departureTime);
            System.out.println("----> Arrival time = " + arrivalTime);
            System.out.println("----> Free trains = " + trainsId);

            List<TrainDTO> trainDTOS = new ArrayList<>();
            trainsId.forEach(id -> trainDTOS.add(trainService.getTrain(id)));

            freeTrains = trainDTOS;
        }

        model.addAttribute("trains", freeTrains);

        if (freeTrains.size() == 0){
            String trainError = "There is no free train for this time period. Create train";
            model.addAttribute("trainError",trainError );
        }

        return "/attach_train";
    }

    @PostMapping("/routes/new/train")
    public String attachTrain(@RequestParam(name = "trainId") int trainId,
                              @RequestParam(name = "routeName") String routeName,
                              Model model,
                              HttpSession session) {

        System.out.println("----> Post Mapping Attach train");

        System.out.println("-----> trainId = " + trainId);
        System.out.println("-----> route name = " + routeName);

        if (session.getAttribute("tempRoutePaths") == null) {

            return "redirect:/routes/new/arcs";

        } else {

            List<RoutePathDTO> tempRoutePaths = (List<RoutePathDTO>) session.getAttribute("tempRoutePaths");

            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setRouteName(routeName);
            routeDTO.setTrain(trainService.getTrain(trainId));

            boolean routeCreated = false;

            int routeId = 0;

            try {
                routeId = stationService.createRoute(routeDTO, tempRoutePaths);

            } catch (UseReservedTrainException e) {
                e.printStackTrace();
                String routeCreationError = e.getMessage();
                model.addAttribute("routeCreationError", routeCreationError);
            }

            if (routeId > 0){
                routeCreated = true;
                model.addAttribute("routeId", routeId);
            }
            model.addAttribute("routeCreated", routeCreated);

        }

        // CLEAN SESSION
        session.removeAttribute("tempRoutePaths");
        session.removeAttribute("firstArcAdded");

        return "/attach_train";
    }
}
