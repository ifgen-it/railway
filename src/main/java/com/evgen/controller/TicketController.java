package com.evgen.controller;

import com.evgen.dao.impl.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.service.StationService;
import com.evgen.service.TicketService;
import com.evgen.service.UserService;
import com.evgen.service.impl.TimeLimitPurchaseException;
import com.evgen.service.impl.TwinUserPurchaseException;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Controller
public class TicketController {

    @Autowired
    private StationService stationService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;


    @GetMapping("/tickets/buy")
    public String getBuyTicket(@RequestParam(name = "routeId", required = false) String strRouteId,
                               @RequestParam(name = "startStationId", required = false) String strStartStationId,
                               @RequestParam(name = "finishStationId", required = false) String strFinishStationId,
                               Model model, HttpSession session) {


        // CHECKING ERRORS
        boolean errorParse = false;
        boolean errorGotNull = false;

        System.out.println("===== in the BUY TICKET GET =======");
        System.out.println("----- RouteId = " + strRouteId);
        System.out.println("----- startStationId = " + strStartStationId);
        System.out.println("----- finishStationId = " + strFinishStationId);

        if (strRouteId == null || strStartStationId == null || strFinishStationId == null) {
            errorGotNull = true;
            System.out.println("===> Got nulls");
            model.addAttribute("errorGotNull", errorGotNull);
            return "/buy_ticket";
        }

        int routeId = 0;
        int startStationId = 0;
        int finishStationId = 0;

        try {
            routeId = Integer.parseInt(strRouteId);
            startStationId = Integer.parseInt(strStartStationId);
            finishStationId = Integer.parseInt(strFinishStationId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorParse = true;
        }

        if (routeId <= 0 || startStationId <= 0 || finishStationId <= 0) {
            errorParse = true;
        }

        if (errorParse == true) {
            System.out.println("===> Error parse");
            model.addAttribute("errorParse", errorParse);
            return "/buy_ticket";
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
            System.out.println("=====> want to get Busy seats =====");
            List<Integer> busySeats = ticketService.getBusySeats(
                    userRoute.getRouteDTO().getRouteId(),
                    userRoute.getRouteDepartureTime(),
                    userRoute.getRouteArrivalTime());

            int seatsAmount = userRoute.getRouteDTO().getTrain().getSeatsAmount();
            System.out.println("======> Busy seats in RouteId " + routeId + " : " + busySeats +
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
            session.setAttribute("allUsers", userService.getAllUsers());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorParse = true;
            session.removeAttribute("ticketDetails");
        }

        model.addAttribute("errorParse", errorParse);
        model.addAttribute("errorGotNull", errorGotNull);

        return "/buy_ticket";
    }


    @PostMapping("/tickets/buy")
    public String buyTicket(@RequestParam(name = "userId") int userId,
                            @RequestParam(name = "seatNumber") int seatNumber,
                            Model model, HttpSession session) {


        System.out.println("============== Ticket buy in the POST ==============");
        System.out.println("---- seat number = " + seatNumber);
        System.out.println("---- user id = " + userId);

        // CHECKING ERRORS
        boolean errorParse = false;
        boolean errorGotNull = false;

        model.addAttribute("errorParse", errorParse);

        if(session.getAttribute("ticketDetails") == null){
            errorGotNull = true;
            model.addAttribute("errorGotNull", errorGotNull);

            return "/buy_ticket";
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
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();

        } catch (TwinUserPurchaseException e) {
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();

        } catch (BusySeatPurchaseException e) {
            e.printStackTrace();
            buyError = true;
            ticketBuyError = e.getMessage();
        }

        if (buyError == false){

            model.addAttribute("ticketId", ticketId);

            // HERE TICKET MUST BE BOUGHT SO TICKET DETAILS CAN BE REMOVED FROM SESSION
            session.removeAttribute("ticketDetails");
            session.removeAttribute("freeSeats");
            session.removeAttribute("freeSeatsAmount");
            session.removeAttribute("allUsers");

            model.addAttribute("ticketBought", true);
        } else {

            // SOMETHING WENT WRONG
            model.addAttribute("ticketBought", false);
            model.addAttribute("ticketBuyError", ticketBuyError);
        }



        return "/buy_ticket";
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

        System.out.println("======== in the Post journey ===========");
        System.out.println("-----> Common routes = " + routesId);

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


            System.out.println("---------> User route: " + userRoute);
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

}
