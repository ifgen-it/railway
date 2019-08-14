package com.evgen.service.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private UserDAO userDAO;

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

    @Override
    public List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {
        return ticketDAO.getBusySeats(routeId, startTime, finishTime);
    }

    @Override
    public boolean buyTicket(RouteExtDTO routeExtDTO, int seatNumber, int userId) {

        // ERRORS WHY USER CANNOT BUY TICKET NEED TO IMPLEMENT WITH DIFF. EXCEPTIONS

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userDAO.get(userId);
        RouteEntity routeEntity = modelMapper.map(routeExtDTO.getRouteDTO(), RouteEntity.class);
        StationEntity startStationEntity = modelMapper.map(routeExtDTO.getRouteBeginStation(), StationEntity.class);
        StationEntity finishStationEntity = modelMapper.map(routeExtDTO.getRouteEndStation(), StationEntity.class);
        LocalDateTime startTime = routeExtDTO.getRouteDepartureTime();
        LocalDateTime finishTime = routeExtDTO.getRouteArrivalTime();
        float price = routeExtDTO.getRoutePrice();

        return ticketDAO.buyTicket(routeEntity, startStationEntity, finishStationEntity,
                startTime, finishTime, seatNumber, userEntity, price);

    }
}
