package com.evgen.dao.impl;

import com.evgen.dao.RouteDAO;
import com.evgen.entity.station.RouteEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class RouteDAOImpl implements RouteDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<RouteEntity> getAll() {
        return em.createQuery("select r from RouteEntity r").getResultList();
    }

    @Override
    public int add(RouteEntity route) {
        em.persist(route);
        return  route.getRouteId();
    }

    @Override
    public RouteEntity get(int id) {

        return em.find(RouteEntity.class, id);
    }

    @Override
    public void remove(RouteEntity route) {

        if (!em.contains(route)){
            route = em.merge(route);
        }
        em.remove(route);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from RouteEntity r where r.routeId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public RouteEntity update(RouteEntity route) {
        return em.merge(route);
    }
}
