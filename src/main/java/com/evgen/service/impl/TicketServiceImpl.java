package com.evgen.service.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public List<TicketDTO> getAllTickets() {

        List<TicketDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        ticketDAO.getAll().forEach(item -> dtos.add(modelMapper.map(item, TicketDTO.class)));

        return dtos;
    }

    @Override
    public int addTicket(TicketDTO ticket) {

        ModelMapper modelMapper = new ModelMapper();
        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);

        return ticketDAO.add(ticketEntity);
    }
}
