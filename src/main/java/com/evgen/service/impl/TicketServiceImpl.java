package com.evgen.service.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dao.impl.BusySeatPurchaseException;
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
    public int buyTicket(RouteExtDTO routeExtDTO, int seatNumber, int userId)
            throws TimeLimitPurchaseException,
            TwinUserPurchaseException,
            BusySeatPurchaseException {

        // ERRORS WHY USER CANNOT BUY TICKET NEED TO IMPLEMENT WITH DIFF. EXCEPTIONS

        boolean timeLimitPurchaseExpired = false;
        // CHECK HERE TIME LIMIT
        LocalDateTime deptTime = routeExtDTO.getRouteDepartureTime();
        LocalDateTime nowTime = LocalDateTime.now();

        if (nowTime.isAfter(deptTime)){
            throw new TimeLimitPurchaseException("Train was gone already. Select another one");
        }

        LocalDateTime purchaseTimeLimit = deptTime.minusMinutes(10);
        if (nowTime.isAfter(purchaseTimeLimit)){
            timeLimitPurchaseExpired = true;
        }

        if (timeLimitPurchaseExpired == true){
            System.out.println("========> Time limit purchase Exception thrown =====");
            throw new TimeLimitPurchaseException("Train will " +
                    "start less than 10 minutes. Ticket purchasing was stopped");
        }

        boolean twinUserOnTheTrain = false;
        // CHECK HERE FOR A EXISTENCE TWIN USER ON THE TRAIN
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userDAO.get(userId);
        RouteEntity routeEntity = modelMapper.map(routeExtDTO.getRouteDTO(), RouteEntity.class);

        if(ticketDAO.getTicket(routeEntity,userEntity) != null){
            twinUserOnTheTrain = true;
        }

        if (twinUserOnTheTrain == true){
            System.out.println("===========> There is twin user on the train");
            throw new TwinUserPurchaseException("User with the same first name, last name and" +
                    " birthday already purchased ticket on this route");
        }


        // CONTINUE TO BUY TICKET

        StationEntity startStationEntity = modelMapper.map(routeExtDTO.getRouteBeginStation(), StationEntity.class);
        StationEntity finishStationEntity = modelMapper.map(routeExtDTO.getRouteEndStation(), StationEntity.class);
        LocalDateTime startTime = routeExtDTO.getRouteDepartureTime();
        LocalDateTime finishTime = routeExtDTO.getRouteArrivalTime();
        float price = routeExtDTO.getRoutePrice();

        int ticketId = 0;
        try {
            ticketId = ticketDAO.buyTicket(routeEntity, startStationEntity, finishStationEntity,
                    startTime, finishTime, seatNumber, userEntity, price);
        } catch (BusySeatPurchaseException e) {
            e.printStackTrace();
            throw e;
        }

        return ticketId;

    }
}
