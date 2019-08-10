package com.evgen.dao.impl;

import com.evgen.dao.RoutePathDAO;
import com.evgen.entity.station.RoutePathEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class RoutePathDAOImpl implements RoutePathDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<RoutePathEntity> getAll() {
        return em.createQuery("select r from RoutePathEntity r").getResultList();
    }

    @Override
    public int add(RoutePathEntity routePath) {
        em.persist(routePath);
        return routePath.getRoutePathId();
    }

    @Override
    public RoutePathEntity get(int id) {

        return em.find(RoutePathEntity.class, id);
    }

    @Override
    public void remove(RoutePathEntity routePath) {

        if (!em.contains(routePath)) {
            routePath = em.merge(routePath);
        }
        em.remove(routePath);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from RoutePathEntity r where r.routePathId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public RoutePathEntity update(RoutePathEntity routePath) {
        return em.merge(routePath);
    }

    @Override
    public List<RoutePathEntity> getArrivals(int stationId) {

        String queryString = "select r from RoutePathEntity r join r.arc a";
        TypedQuery<RoutePathEntity> query = em.createQuery(queryString, RoutePathEntity.class);

        List<RoutePathEntity> results = query.getResultList();

        for (RoutePathEntity item : results){
            System.out.println("--> item: " + item);
        }

        return results;
    }
}
