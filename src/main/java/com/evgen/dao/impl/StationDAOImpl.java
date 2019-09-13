package com.evgen.dao.impl;

import com.evgen.controller.MessageController;
import com.evgen.dao.StationDAO;
import com.evgen.entity.station.StationEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StationDAOImpl implements StationDAO {

    private static final Logger logger = Logger.getLogger(StationDAOImpl.class);

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<StationEntity> getAll() {

        return em.createQuery("select s from StationEntity s order by s.stationId asc").getResultList();
    }

    @Override
    public int add(StationEntity station) {
        em.persist(station);
        return station.getStationId();
    }

    @Override
    public StationEntity get(int id) {

        return em.find(StationEntity.class, id);
    }

    @Override
    public void remove(StationEntity station) {

        if (!em.contains(station)) {
            station = em.merge(station);
        }
        em.remove(station);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from StationEntity s where s.stationId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public StationEntity update(StationEntity station) {
        return em.merge(station);
    }

    @Override
    public StationEntity getByName(String stationName) {

        String queryString = "select s from StationEntity s where s.stationName = :stationName";
        Query query = em.createQuery(queryString);
        query.setParameter("stationName", stationName);
        List<StationEntity> stations = (List<StationEntity>) query.getResultList();

        if (stations.size() == 0)
            return null;
        else
            return stations.get(0);
    }

    @Override
    public List<String> getStationNames(int routeId) {

        Query query = em.createNativeQuery("(select s.station_name st " +
                "from routepath r " +
                "join arc a on (r.arc_id = a.arc_id) " +
                "join station s on (a.begin_station = s.station_id) " +
                "where route_id = ?1) " +
                "union " +
                "(select s.station_name " +
                "from routepath r " +
                "join arc a on (r.arc_id = a.arc_id) " +
                "join station s on (a.end_station = s.station_id) " +
                "where route_id = ?1) ");
        query.setParameter(1, routeId);
        List<String> results = query.getResultList();

        logger.info("Station names" + results);

        return results;
    }
}
