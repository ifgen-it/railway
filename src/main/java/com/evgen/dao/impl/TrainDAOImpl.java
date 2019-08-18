package com.evgen.dao.impl;

import com.evgen.dao.TrainDAO;
import com.evgen.entity.train.TrainEntity;
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
public class TrainDAOImpl implements TrainDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    public List<TrainEntity> getAll() {
        return em.createQuery("select t from TrainEntity t").getResultList();
    }

    @Override
    public int add(TrainEntity train) {
        em.persist(train);
        return train.getTrainId();
    }

    @Override
    public TrainEntity get(int id) {

        return em.find(TrainEntity.class, id);
    }

    @Override
    public void remove(TrainEntity train) {

        if (!em.contains(train)) {
            train = em.merge(train);
        }
        em.remove(train);
    }

    @Override
    public void removeWith(int id) {

        String queryString = "delete from TrainEntity t where t.trainId = " + id;
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    @Override
    public TrainEntity update(TrainEntity train) {
        return em.merge(train);
    }

    @Override
    public TrainEntity getByName(String trainName) {

        String queryString = "select t from TrainEntity t where t.trainName = :trainName";
        Query query = em.createQuery(queryString);
        query.setParameter("trainName", trainName);
        List<TrainEntity> trains = (List<TrainEntity>) query.getResultList();

        if (trains.size() == 0)
            return null;
        else
            return trains.get(0);
    }

    @Override
    public List<Integer> getFreeTrains(LocalDateTime startTime, LocalDateTime finishTime) {

        String subQueryString = "select distinct r.route.train.trainId " +
                "from RoutePathEntity r " +
                "where " +
                "( r.arrivalTime >= :startTime and r.arrivalTime <= :finishTime ) " +
                "or " +
                "( r.departureTime >= :startTime and r.departureTime <= :finishTime ) " +
                "or " +
                "( r.departureTime < :startTime and r.arrivalTime > :finishTime ) ";

        String queryString = "select t.trainId from TrainEntity t " +
                "where t.trainId not in ( " + subQueryString + " )";


        TypedQuery<Integer> query = em.createQuery(queryString, Integer.class);
        query.setParameter("startTime", startTime);
        query.setParameter("finishTime", finishTime);

        List<Integer> result =  query.getResultList();

        return result;
    }
}
