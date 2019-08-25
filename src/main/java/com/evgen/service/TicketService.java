package com.evgen.service;

import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllTickets();

    int addTicket(TicketDTO ticket);

    List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime);

    int buyTicket(RouteExtDTO routeExtDTO, int seatNumber, int userId)
            throws TimeLimitPurchaseException,
            TwinUserPurchaseException,
            BusySeatPurchaseException;

    List<TicketDTO> getTickets(int routeId);

    TicketDTO get(int ticketId);

}
