package com.evgen.dao.impl;

import com.evgen.dao.ArcDAO;
import com.evgen.entity.station.ArcEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class ArcDAOImpl implements ArcDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<ArcEntity> getAll() {

        return em.createQuery("select a from ArcEntity a").getResultList();
    }

    @Override
    public int add(ArcEntity arc) {

        em.persist(arc);
        return arc.getArcId();
    }

    @Override
    public ArcEntity get(int id) {

        return em.find(ArcEntity.class, id);
    }

    @Override
    public void remove(ArcEntity arc) {

        if (!em.contains(arc)){
            arc = em.merge(arc);
        }
        em.remove(arc);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from ArcEntity a where a.arcId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public ArcEntity update(ArcEntity arc) {

        return em.merge(arc);
    }

    @Override
    public ArcEntity getByStations(int beginStationId, int endStationId) {

        String queryString = "select a from ArcEntity a " +
                "where a.beginStation.stationId = :beginStationId and " +
                "a.endStation.stationId = :endStationId";

        Query query = em.createQuery(queryString);
        query.setParameter("beginStationId", beginStationId);
        query.setParameter("endStationId", endStationId);

        List<ArcEntity> arcs = (List<ArcEntity>) query.getResultList();

        if (arcs.size() == 0)
            return null;
        else
            return arcs.get(0);
    }
}
