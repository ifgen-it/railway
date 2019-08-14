package com.evgen.service;

import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllTickets();

    int addTicket(TicketDTO ticket);

    List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime);

    boolean buyTicket(RouteExtDTO routeExtDTO, int seatNumber, int userId);


}
