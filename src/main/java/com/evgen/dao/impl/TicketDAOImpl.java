package com.evgen.dao.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class TicketDAOImpl implements TicketDAO {

    private static final Logger logger = Logger.getLogger(TicketDAOImpl.class);

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<TicketEntity> getAll() {
        return em.createQuery("select t from TicketEntity t").getResultList();
    }

    @Override
    public int add(TicketEntity ticket) {
        em.persist(ticket);
        return ticket.getTicketId();
    }

    @Override
    public TicketEntity get(int id) {

        return em.find(TicketEntity.class, id);
    }

    @Override
    public void remove(TicketEntity ticket) {

        if (!em.contains(ticket)) {
            ticket = em.merge(ticket);
        }
        em.remove(ticket);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from TicketEntity t where t.ticketId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public TicketEntity update(TicketEntity ticket) {
        return em.merge(ticket);
    }

    @Override
    public List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {

        String queryString = "select t.seatNumber from TicketEntity t " +
                "where t.ticketRoute.routeId = :routeId " +
                "and ( " +
                "( t.startTime >= :startTime and t.startTime < :finishTime ) " +
                "or " +
                "( t.finishTime > :startTime and t.finishTime <= :finishTime ) " +
                "or " +
                "( t.startTime <= :startTime and t.finishTime >= :finishTime ) " +
                ")";

        TypedQuery<Integer> query = em.createQuery(queryString, Integer.class);
        query.setParameter("routeId", routeId);
        query.setParameter("startTime", startTime);
        query.setParameter("finishTime", finishTime);

        List<Integer> result = query.getResultList();

        return result;
    }

    @Override
    public int buyTicket(RouteEntity ticketRoute,
                             StationEntity startStation, StationEntity finishStation,
                             LocalDateTime startTime, LocalDateTime finishTime,
                             int seatNumber, UserEntity user,
                             float price) throws BusySeatPurchaseException {

        // NEED TO CHECK IF SEAT NUMBER ALREADY PURCHASED

        // MAY BE NEED TO SET TRANSACTIONAL MODE TO OTHER DB USERS CANNOT
        // WRITE INTO TABLE TICKETS WHILE RUNNING THIS METHOD

        logger.info("In the Ticket DAO : buyTicket");
        List<Integer> busySeats = getBusySeats(ticketRoute.getRouteId(), startTime, finishTime);
        logger.info("BusySeats: " + busySeats);

        boolean busySeatError = busySeats.contains(Integer.valueOf(seatNumber));

        // WILL BE BETTER THROW EXCEPTION WITH PROBLEM DESCRIPTION
        if (busySeatError == true){
            throw new BusySeatPurchaseException("Seat number " + seatNumber + " was purchased recently. Select another one");
        }

        // MAKE TICKET
        TicketEntity ticketEntity = new TicketEntity(startTime, finishTime,
                seatNumber, price, ticketRoute, user,
                startStation, finishStation);
        int ticketId = add(ticketEntity);

        logger.info("Ticket was saved, ticketId = " + ticketId);

        return ticketId;
    }

    @Override
    public TicketEntity getTicket(RouteEntity route, UserEntity user) {

        String queryString = "select t from TicketEntity t " +
                "where t.ticketRoute = :route " +
                "and t.user = :user ";

        TypedQuery<TicketEntity> query = em.createQuery(queryString, TicketEntity.class);
        query.setParameter("route", route);
        query.setParameter("user", user);

        List<TicketEntity> tickets = query.getResultList();
        logger.info("Get Ticket : " + tickets);
        if(tickets.size() == 0){
            return null;
        }
        else {
            return tickets.get(0);
        }
    }

    @Override
    public List<TicketEntity> getTickets(int routeId) {

        String queryString = "select t from TicketEntity t " +
                "where t.ticketRoute.routeId = :routeId";

        TypedQuery<TicketEntity> query = em.createQuery(queryString, TicketEntity.class);
        query.setParameter("routeId", routeId);

        List<TicketEntity> tickets = query.getResultList();
        logger.info("Ticket : " + tickets);

        return tickets;
    }
}
