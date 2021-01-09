package com.evgen.rest;

import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.service.TicketService;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketRestController {

    private TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public List<TicketDTO> getAllTickets() {

        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/route/{id}")
    public List<TicketDTO> getAllTicketsByRouteId(@PathVariable("id") Integer id) {

        return ticketService.getTickets(id);
    }

    @GetMapping("/ticket/{id}")
    public TicketDTO getTicket(@PathVariable("id") Integer id) {

        return ticketService.get(id);
    }

    // GET /trains/route/{id}/busy-seats?startTime=2021-01-08T16:57:48&finishTime=2021-01-09T16:57:48
    @GetMapping("/tickets/route/{id}/busy-seats")
    public List<Integer> getBusySeats(@PathVariable("id") Integer id,
                                      @RequestParam("startTime") String strStartTime,
                                      @RequestParam("finishTime") String strFinishTime) {

        LocalDateTime startTime = LocalDateTime.parse(strStartTime);
        LocalDateTime finishTime = LocalDateTime.parse(strFinishTime);
        return ticketService.getBusySeats(id, startTime, finishTime);
    }

    // POST /ticket?seatNumber=6&userId=1
    @PostMapping("/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createTicket(@RequestParam("seatNumber") Integer seatNumber,
                                @RequestParam("userId") Integer userId,
                                @RequestBody RouteExtDTO routeExt) {

        int ticketId = 0;
        try {
            ticketId = ticketService.buyTicket(routeExt, seatNumber, userId);

        } catch (TimeLimitPurchaseException e) {
            ticketId = -1;
        } catch (
                TwinUserPurchaseException e) {
            ticketId = -2;
        } catch (BusySeatPurchaseException e) {
            ticketId = -3;
        }

        return ticketId;
    }


}
