package com.evgen.service;

import com.evgen.dto.ticket.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAllTickets();

    int addTicket(TicketDTO ticket);


}
