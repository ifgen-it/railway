package com.evgen.dao;

import com.evgen.dao.impl.BusySeatPurchaseException;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketDAO {

    List<TicketEntity> getAll();

    int add(TicketEntity ticket);

    TicketEntity get(int id);

    void remove(TicketEntity ticket);

    void removeWith(int id);

    TicketEntity update(TicketEntity ticket);

    List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime);

    int buyTicket(RouteEntity ticketRoute,
                      StationEntity startStation, StationEntity finishStation,
                      LocalDateTime startTime, LocalDateTime finishTime,
                      int seatNumber, UserEntity user, float price) throws BusySeatPurchaseException;

    TicketEntity getTicket(RouteEntity route, UserEntity user);


}
