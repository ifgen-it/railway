package com.evgen.dao;

import com.evgen.entity.ticket.TicketEntity;

import java.util.List;

public interface TicketDAO {

    List<TicketEntity> getAll();

    int add(TicketEntity ticket);

    TicketEntity get(int id);

    void remove(TicketEntity ticket);

    void removeWith(int id);

    TicketEntity update(TicketEntity ticket);
}
