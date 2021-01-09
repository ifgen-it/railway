package com.evgen.controller;

import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.SecurityService;
import com.evgen.service.StationService;
import com.evgen.service.TicketService;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    private static final Logger logger = Logger.getLogger(TicketController.class);

    private StationService stationService;

    private TicketService ticketService;

    private SecurityService securityService;

    public TicketController(@Qualifier("stationServiceRestClient") StationService stationService,
                            @Qualifier("ticketServiceRestClient") TicketService ticketService,
                            SecurityService securityService) {
        this.stationService = stationService;
        this.ticketService = ticketService;
        this.securityService = securityService;
    }

    @GetMapping("/ticket/details")
    public String getTicketDetails(@RequestParam(name = "routeId", required = false) String strRouteId,
                               @RequestParam(name = "startStationId", required = false) String strStartStationId,
                               @RequestParam(name = "finishStationId", required = false) String strFinishStationId,
                               Model model, HttpSession session) {


        // CHECKING ERRORS
        boolean errorParse = false;
        boolean errorGotNull = false;

        logger.info("BUY TICKET");
        logger.info("RouteId = " + strRouteId);
        logger.info("startStationId = " + strStartStationId);
        logger.info("finishStationId = " + strFinishStationId);

        if (strRouteId == null || strStartStationId == null || strFinishStationId == null) {
            errorGotNull = true;
            logger.warn("Got nulls");
            model.addAttribute("errorGotNull", errorGotNull);
            return "/ticket_details";
        }

        int routeId = 0;
        int startStationId = 0;
        int finishStationId = 0;

        try {
            routeId = Integer.parseInt(strRouteId);
            startStationId = Integer.parseInt(strStartStationId);
            finishStationId = Integer.parseInt(strFinishStationId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            errorParse = true;
        }

        if (routeId <= 0 || startStationId <= 0 || finishStationId <= 0) {
            errorParse = true;
        }

        if (errorParse == true) {
            logger.warn("Error parse");
            model.addAttribute("errorParse", errorParse);
            return "/ticket_details";
        }

        // ALL RIGHT - CONTINUE TO BUY TICKET


        try {
            RouteExtDTO userRoute = new RouteExtDTO();
            userRoute.setRouteDTO(stationService.getRoute(routeId));
            userRoute.setRouteDepartureTime(
                    stationService.getRouteStartTime(routeId, startStationId));
            userRoute.setRouteArrivalTime(
                    stationService.getRouteFinishTime(routeId, finishStationId));

            userRoute.setRouteBeginStation(stationService.getStation(startStationId));
            userRoute.setRouteEndStation(stationService.getStation(finishStationId));

            userRoute.setRouteLength(
                    stationService.getRouteLength(
                            routeId, userRoute.getRouteDepartureTime(), userRoute.getRouteArrivalTime()
                    )
            );
            userRoute.setRoutePrice(RouteExtDTO.makePrice(userRoute.getRouteLength()));

            //model.addAttribute("userRoute", userRoute);
            session.setAttribute("ticketDetails", userRoute);


            // GET BUSY SEATS IN TRAIN
            List<Integer> busySeats = ticketService.getBusySeats(
                    userRoute.getRouteDTO().getRouteId(),
                    userRoute.getRouteDepartureTime(),
                    userRoute.getRouteArrivalTime());

            int seatsAmount = userRoute.getRouteDTO().getTrain().getSeatsAmount();
            logger.info("Busy seats in RouteId " + routeId + " : " + busySeats +
                    " of " + seatsAmount);

            // GET FREE SEATS IN TRAIN

            boolean[] freeSeatsArr = new boolean[seatsAmount];
            for (int i = 0; i < freeSeatsArr.length; i++) {
                freeSeatsArr[i] = true;
            }
            for (Integer busySeat : busySeats) {
                freeSeatsArr[busySeat - 1] = false;
            }

            List<Integer> freeSeats = new ArrayList<>();
            for (int i = 0; i < freeSeatsArr.length; i++) {
                if (freeSeatsArr[i] == true) {
                    freeSeats.add(i + 1);
                }
            }
            session.setAttribute("freeSeats", freeSeats);
            session.setAttribute("freeSeatsAmount", freeSeats.size());

            // GET ALL USERS
            //session.setAttribute("allUsers", userService.getAllUsers());

        } catch (Exception e) {
            logger.warn(e.getMessage());
            errorParse = true;
            session.removeAttribute("ticketDetails");
        }

        model.addAttribute("errorParse", errorParse);
        model.addAttribute("errorGotNull", errorGotNull);

        return "/ticket_details";
    }



    @GetMapping("/ticket/buy")
    public String getBuyTicket() {

        return "redirect:/ticket/details";
    }

    @PostMapping("/ticket/buy")
    public String buyTicket(@RequestParam(name = "seatNumber") int seatNumber,
                            Model model, HttpSession session) {


        UserDTO userDTO = securityService.getAuthUser();

        // THIS CASE FOR INSURANCE, REALLY SECURITY WILL REDIRECT GUEST TO THE LOGIN PAGE
        // AND AFTER LOGIN SECURITY PERMIT TO GO INTO THIS METHOD TO BUY TICKET
        if (userDTO == null){
            return "redirect:/login";
        }

        int userId = userDTO.getUserId();

        logger.info("seat number = " + seatNumber);
        logger.info("user id = " + userId);

        // CHECKING ERRORS
        boolean errorParse = false;
        boolean errorGotNull = false;

        model.addAttribute("errorParse", errorParse);

        if(session.getAttribute("ticketDetails") == null){
            errorGotNull = true;
            model.addAttribute("errorGotNull", errorGotNull);

            return "redirect:/ticket/details";
        }

        model.addAttribute("errorGotNull", errorGotNull);

        RouteExtDTO userRoute = (RouteExtDTO) session.getAttribute("ticketDetails");
        model.addAttribute("userRoute", userRoute);


        //TICKET BUYING:
        int ticketId = 0;
        boolean buyError = false;
        String ticketBuyError = "";

        try {
            ticketId = ticketService.buyTicket(userRoute, seatNumber, userId);

        } catch (TimeLimitPurchaseException e) {
            logger.warn("TimeLimitPurchaseException caught");
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();

        } catch (TwinUserPurchaseException e) {
            logger.warn("TwinUserPurchaseException caught");
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();

        } catch (BusySeatPurchaseException e) {
            logger.warn("BusySeatPurchaseException caught");
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();
        }

        if (buyError == false){

            model.addAttribute("ticket", ticketService.get(ticketId));
            model.addAttribute("userId", userId);
            logger.info("userId = " + userId);

            // HERE TICKET MUST BE BOUGHT SO TICKET DETAILS CAN BE REMOVED FROM SESSION
            session.removeAttribute("ticketDetails");
            session.removeAttribute("freeSeats");
            session.removeAttribute("freeSeatsAmount");
            //session.removeAttribute("allUsers");

            model.addAttribute("ticketBought", true);
        } else {

            // SOMETHING WENT WRONG
            model.addAttribute("ticketBought", false);
            model.addAttribute("ticketBuyError", ticketBuyError);
        }

        return "/ticket_buy";
    }

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

        logger.info("Journey");
        logger.info("begin station id = " + beginStationId);
        logger.info("end station id = " + endStationId);
        logger.info("end date from = " + strDateFrom);
        logger.info("end date to = " + strDateTo);

        // CHECK DATES
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(strDateFrom);
        } catch (ParseException e) {
            logger.warn("dateFrom error");
            error = true;
            dateFromError = "Date From is required";
        }
        try {
            dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(strDateTo);
        } catch (ParseException e) {
            logger.warn("dateTo error");
            error = true;
            dateToError = "Date To is required";
        }

        if (error == false && (dateTo.before(dateFrom) || dateTo.equals(dateFrom))) {
            error = true;
            dateToError = "Date To must be after Date From";
        }

        // CHECK STATIONS
        if (beginStationId == 0 || endStationId == 0) {

            if (beginStationId == 0) {
                beginStationError = "Select begin station";
                error = true;
            }
            if (endStationId == 0) {
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

        logger.info("Common routes = " + routesId);

        // LIST OF ROUTES WITH START-END STATIONS : USERS START-END STATIONS
        List<RouteExtDTO> userRoutes = new ArrayList<>();
        for (Integer routeId : routesId) {

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
            userRoute.setRoutePrice(RouteExtDTO.makePrice(userRoute.getRouteLength()));


            logger.info("User route: " + userRoute);
            userRoutes.add(userRoute);
        }

        List<RouteExtDTO> futureRoutes = new ArrayList<>();
        List<RouteExtDTO> pastRoutes = new ArrayList<>();

        LocalDateTime timeNow = LocalDateTime.now();
        userRoutes.forEach(route -> {
            if (route.getRouteDepartureTime().isAfter(timeNow)){
                futureRoutes.add(route);
            } else {
                pastRoutes.add(route);
            }
        });

        //CONVERT DATE
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fmtDateFrom = formatter.format(dateFrom);
        String fmtDateTo = formatter.format(dateTo);

        //SET MODEL ATTRIBUTES
        model.addAttribute("futureRoutes", futureRoutes);
        model.addAttribute("pastRoutes", pastRoutes);

        model.addAttribute("futureRoutesNum", futureRoutes.size());
        model.addAttribute("pastRoutesNum", pastRoutes.size());

        model.addAttribute("beginStationName", stationService.getStation(beginStationId).getStationName());
        model.addAttribute("endStationName", stationService.getStation(endStationId).getStationName());
        model.addAttribute("dateFrom", fmtDateFrom);
        model.addAttribute("dateTo", fmtDateTo);


        return "/journey";
    }


    @GetMapping("/passengers")
    public String getPassengers(Model model) {

        logger.info("Get passengers");
        model.addAttribute("routesExt", stationService.getAllRoutesExt());

        return "/passengers";
    }

    @PostMapping("/passengers")
    public String passengers(@RequestParam(name = "routeId") int routeId,
                            Model model) {

        model.addAttribute("routesExt", stationService.getAllRoutesExt());

        List<TicketDTO> tickets;
        boolean allRoutes = true;
        RouteExtDTO searchRoute = null;

        if (routeId == 0){
            tickets = ticketService.getAllTickets();
        } else {
            tickets = ticketService.getTickets(routeId);
            allRoutes = false;
            searchRoute = stationService.getRouteExt(routeId);
        }

        model.addAttribute("tickets", tickets);
        model.addAttribute("allRoutes", allRoutes);
        model.addAttribute("searchRoute", searchRoute);

        return "/passengers";
    }
}
