package com.evgen.service.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.TicketService;
import com.evgen.service.UserService;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = Logger.getLogger(TicketServiceImpl.class);

    private TicketDAO ticketDAO;

    private UserService userService;

    public TicketServiceImpl(TicketDAO ticketDAO,
                             @Qualifier("userServiceRestClient") UserService userService) {
        this.ticketDAO = ticketDAO;
        this.userService = userService;
    }

    @Override
    public List<TicketDTO> getAllTickets() {

        List<TicketDTO> dtos = new ArrayList<>();
        ticketDAO.getAll().forEach(item -> dtos.add(TicketDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public int addTicket(TicketDTO ticket) {

        TicketEntity ticketEntity = TicketDTO.dtoToEntity(ticket);

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
            logger.warn("TimeLimitPurchaseException: Train was gone already. Select another one");
            throw new TimeLimitPurchaseException("Train was gone already. Select another one");
        }

        LocalDateTime purchaseTimeLimit = deptTime.minusMinutes(10);
        if (nowTime.isAfter(purchaseTimeLimit)){
            timeLimitPurchaseExpired = true;
        }

        if (timeLimitPurchaseExpired == true){
            logger.warn("TimeLimitPurchaseException: Train will start less than 10 minutes. Ticket purchasing was stopped");
            throw new TimeLimitPurchaseException("Train will " +
                    "start less than 10 minutes. Ticket purchasing was stopped");
        }

        boolean twinUserOnTheTrain = false;
        // CHECK HERE FOR A EXISTENCE TWIN USER ON THE TRAIN

        UserDTO userDTO = userService.getUser(userId);
        UserEntity userEntity = UserDTO.dtoToEntity(userDTO);

        RouteEntity routeEntity = RouteDTO.dtoToEntity(routeExtDTO.getRouteDTO());

        if(ticketDAO.getTicket(routeEntity,userEntity) != null){
            twinUserOnTheTrain = true;
        }

        if (twinUserOnTheTrain == true){
            logger.warn("TwinUserPurchaseException: User with the same first name," +
                    " last name and birthday already purchased ticket on this route");
            throw new TwinUserPurchaseException("User with the same first name, last name and" +
                    " birthday already purchased ticket on this route");
        }


        // CONTINUE TO BUY TICKET
        StationEntity startStationEntity = StationDTO.dtoToEntity(routeExtDTO.getRouteBeginStation());
        StationEntity finishStationEntity = StationDTO.dtoToEntity(routeExtDTO.getRouteEndStation());

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

    @Override
    public List<TicketDTO> getTickets(int routeId) {

        List<TicketDTO> dtos = new ArrayList<>();

        ticketDAO.getTickets(routeId).forEach(item -> dtos.add(TicketDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public TicketDTO get(int ticketId) {

        TicketEntity ticketEntity = ticketDAO.get(ticketId);

        if (ticketEntity == null){
            return null;
        }
        else {
            return TicketDTO.entityToDTO(ticketEntity);
        }
    }
}
