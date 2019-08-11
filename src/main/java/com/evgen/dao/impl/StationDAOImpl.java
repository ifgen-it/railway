package com.evgen.dao.impl;

import com.evgen.dao.StationDAO;
import com.evgen.entity.station.StationEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class StationDAOImpl implements StationDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<StationEntity> getAll() {

        return em.createQuery("select s from StationEntity s order by s.stationId asc").getResultList();
    }

    @Override
    public int add(StationEntity station) {
        em.persist(station);
        return  station.getStationId();
    }

    @Override
    public StationEntity get(int id) {

        return em.find(StationEntity.class, id);
    }

    @Override
    public void remove(StationEntity station) {

        if (!em.contains(station)){
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
}
