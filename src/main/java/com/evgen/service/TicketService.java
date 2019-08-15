package com.evgen.service;

import com.evgen.dao.impl.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.service.impl.TimeLimitPurchaseException;
import com.evgen.service.impl.TwinUserPurchaseException;

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


}
