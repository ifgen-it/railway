package com.evgen.dao.impl;

import com.evgen.dao.TicketDAO;
import com.evgen.entity.ticket.TicketEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class TicketDAOImpl implements TicketDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<TicketEntity> getAll() {
        return em.createQuery("select t from TicketEntity t").getResultList();
    }

    @Override
    public int add(TicketEntity ticket) {
        em.persist(ticket);
        return  ticket.getTicketId();
    }

    @Override
    public TicketEntity get(int id) {

        return em.find(TicketEntity.class, id);
    }

    @Override
    public void remove(TicketEntity ticket) {

        if (!em.contains(ticket)){
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
}
