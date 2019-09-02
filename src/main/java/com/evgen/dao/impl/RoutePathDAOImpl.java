package com.evgen.dao.impl;

import com.evgen.dao.RoutePathDAO;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.RoutePathEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        String queryString = "select r from RoutePathEntity r join r.arc a " +
                "where a.endStation.stationId = :stationId order by r.arrivalTime asc";

        TypedQuery<RoutePathEntity> query = em.createQuery(queryString, RoutePathEntity.class);
        query.setParameter("stationId", stationId);

        List<RoutePathEntity> results = query.getResultList();

        for (RoutePathEntity item : results){
            System.out.println("--> item: " + item);
        }

        return results;
    }

    @Override
    public List<RoutePathEntity> getDepartures(int stationId) {

//        System.out.println("---> In getDepartures");
//        LocalDateTime dateTimeNow = LocalDateTime.now();
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String dateToday = dateTimeNow.format(fmt);
//        System.out.println("Today = " + dateToday);
//
//        dateToday += "%";

//        String queryString = "select r from RoutePathEntity r join r.arc a " +
//                "where a.beginStation.stationId = :stationId " +
//                "and r.departureTime like :dateToday " +
//                "order by r.departureTime asc ";

        String queryString = "select r from RoutePathEntity r join r.arc a " +
                "where a.beginStation.stationId = :stationId order by r.departureTime asc";

        TypedQuery<RoutePathEntity> query = em.createQuery(queryString, RoutePathEntity.class);
        query.setParameter("stationId", stationId);
        List<RoutePathEntity> results = query.getResultList();

        return results;
    }

    @Override
    public RoutePathEntity getFirstArc(int routeId) {

        String subQueryString = "select min(r.departureTime) from RoutePathEntity r " +
                "where r.route.routeId = :routeId";

        String queryString = "select r from RoutePathEntity r " +
                "where r.route.routeId = :routeId and r.departureTime = ( "+ subQueryString +" )";

        TypedQuery<RoutePathEntity> query = em.createQuery(queryString, RoutePathEntity.class);
        query.setParameter("routeId", routeId);

        RoutePathEntity result = query.getSingleResult();

        return result;
    }

    @Override
    public RoutePathEntity getLastArc(int routeId) {

        String subQueryString = "select max (r.arrivalTime) from RoutePathEntity r " +
                "where r.route.routeId = :routeId";

        String queryString = "select r from RoutePathEntity r " +
                "where r.route.routeId = :routeId and r.arrivalTime = ( "+ subQueryString +" )";

        TypedQuery<RoutePathEntity> query = em.createQuery(queryString, RoutePathEntity.class);
        query.setParameter("routeId", routeId);

        RoutePathEntity result = query.getSingleResult();

        return result;
    }

    @Override
    public List<Integer> getCommonRoutes(int startStationId, int finishStationId,
                                         Date dateFrom, Date dateTo) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDateFrom = formatter.format(dateFrom);
        String strDateTo = formatter.format(dateTo);


        String queryStringStartRoutes = "select r.route.routeId from RoutePathEntity r " +
                "join r.arc a " +
                "where a.beginStation.stationId = :startStationId " +
                "and departure_time > (str_to_date( :strDateFrom , '%Y-%m-%d')) " +
                "and departure_time < (str_to_date( :strDateTo , '%Y-%m-%d')) ";

        String queryStringFinishRoutes = "select r.route.routeId from RoutePathEntity r " +
                "join r.arc a " +
                "where a.endStation.stationId = :finishStationId ";

        String queryStringIntersect = queryStringStartRoutes +
                " and r.route.routeId in ( " + queryStringFinishRoutes + " )";

        TypedQuery<Integer> query = em.createQuery(queryStringIntersect, Integer.class);
        query.setParameter("startStationId", startStationId);
        query.setParameter("finishStationId", finishStationId);
        query.setParameter("strDateFrom", strDateFrom);
        query.setParameter("strDateTo", strDateTo);

        List<Integer> result =  query.getResultList();

        return result;
    }

    @Override
    public LocalDateTime getRouteStartTime(int routeId, int startStationId) {

        String queryString = "select r.departureTime from RoutePathEntity r " +
                "join r.arc a " +
                "where r.route.routeId = :routeId " +
                "and a.beginStation.stationId = :startStationId";

        TypedQuery<LocalDateTime> query = em.createQuery(queryString, LocalDateTime.class);
        query.setParameter("routeId", routeId);
        query.setParameter("startStationId", startStationId);

        LocalDateTime result = query.getSingleResult();

        System.out.println("====> getRouteStartTime = " + result + ", routeId = " + routeId + ", startStationId = " + startStationId);
        return result;
    }

    @Override
    public LocalDateTime getRouteFinishTime(int routeId, int finishStationId) {

        String queryString = "select r.arrivalTime from RoutePathEntity r " +
                "join r.arc a " +
                "where r.route.routeId = :routeId " +
                "and a.endStation.stationId = :finishStationId";

        TypedQuery<LocalDateTime> query = em.createQuery(queryString, LocalDateTime.class);
        query.setParameter("routeId", routeId);
        query.setParameter("finishStationId", finishStationId);

        LocalDateTime result = query.getSingleResult();
        System.out.println("====> getRouteFinishTime = " + result + ", routeId = " + routeId + ", finishStationId = " + finishStationId);
        return result;
    }

    @Override
    public Integer getRouteLength(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {

        String queryString = "select sum(a.length) from RoutePathEntity r " +
                "join r.arc a " +
                "where r.route.routeId = :routeId " +
                "and r.departureTime >= :startTime " +
                "and r.arrivalTime <= :finishTime";

        TypedQuery<Long> query = em.createQuery(queryString, Long.class);
        query.setParameter("routeId", routeId);
        query.setParameter("startTime", startTime);
        query.setParameter("finishTime", finishTime);

        Long result = query.getSingleResult();

        return result.intValue();
    }
}
