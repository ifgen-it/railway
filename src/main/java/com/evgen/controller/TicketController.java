package com.evgen.controller;

import com.evgen.dto.station.RouteExtDTO;
import com.evgen.service.StationService;
import com.evgen.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                          @RequestParam(name = "dateFrom") String strDateFrom,
                          @RequestParam(name = "dateTo") String strDateTo,
                          Model model) {

        // CHECKING FOR ERRORS
        String beginStationError = "";
        String endStationError = "";
        String dateFromError = "";
        String dateToError = "";
        boolean error = false;

        System.out.println("===== in the Journey Post =======");
        System.out.println("----- begin station id = " + beginStationId);
        System.out.println("----- end station id = " + endStationId);
        System.out.println("----- end date from = " + strDateFrom);
        System.out.println("----- end date to = " + strDateTo);

        // CHECK DATES
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(strDateFrom);
        } catch (ParseException e) {
            System.out.println("---> dateFrom error");
            error = true;
            dateFromError = "Date From is required";
        }
        try {
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(strDateTo);
        } catch (ParseException e) {
            System.out.println("---> dateTo error");
            error = true;
            dateToError = "Date To is required";
        }

        if (error == false && ( dateTo.before(dateFrom) || dateTo.equals(dateFrom))){
            error = true;
            dateToError = "Date To must be after Date From";
        }

        // CHECK STATIONS
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
            model.addAttribute("dateFromError", dateFromError);
            model.addAttribute("dateToError", dateToError);

            model.addAttribute("stations", stationService.getAllStations());
            return "/journey";
        }

        model.addAttribute("stations", stationService.getAllStations());

        List<Integer> routesId = stationService.getCommonRoutes(beginStationId, endStationId, dateFrom, dateTo);

        System.out.println("======== in the Post journey ===========");
        System.out.println("-----> Common routes = " + routesId);

        // LIST OF ROUTES WITH START-END STATIONS : USERS START-END STATIONS
        List<RouteExtDTO> userRoutes = new ArrayList<>();
        for ( Integer routeId : routesId ) {

            RouteExtDTO userRoute = new RouteExtDTO();
            userRoute.setRouteDTO(stationService.getRoute(routeId));
            userRoute.setRouteDepartureTime(
                    stationService.getRouteStartTime(routeId, beginStationId));
            userRoute.setRouteArrivalTime(
                    stationService.getRouteFinishTime(routeId, endStationId));

            userRoute.setRouteBeginStation(stationService.getStation(beginStationId));
            userRoute.setRouteEndStation(stationService.getStation(endStationId));

            userRoute.setRouteLength(
                    stationService.getRouteLength(
                            routeId, userRoute.getRouteDepartureTime(), userRoute.getRouteArrivalTime()
                    )
            );
            userRoute.setRoutePrice(userRoute.getRouteLength() * 5);


            System.out.println("---------> User route: " + userRoute);
            userRoutes.add(userRoute);
        }

        //CONVERT DATE
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fmtDateFrom = formatter.format(dateFrom);
        String fmtDateTo = formatter.format(dateTo);

        //SET MODEL ATTRIBUTES
        model.addAttribute("directRoutes", userRoutes);
        model.addAttribute("directRoutesNum", userRoutes.size());
        model.addAttribute("beginStationName", stationService.getStation(beginStationId).getStationName());
        model.addAttribute("endStationName", stationService.getStation(endStationId).getStationName());
        model.addAttribute("dateFrom", fmtDateFrom);
        model.addAttribute("dateTo", fmtDateTo);


        return "/journey";
    }

}
