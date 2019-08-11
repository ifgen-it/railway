package com.evgen.dao;

import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.RoutePathEntity;

import java.util.List;

public interface RoutePathDAO {

    List<RoutePathEntity> getAll();

    int add(RoutePathEntity routePath);

    RoutePathEntity get(int id);

    void remove(RoutePathEntity routePath);

    void removeWith(int id);

    RoutePathEntity update(RoutePathEntity routePath);

    List<RoutePathEntity> getArrivals(int stationId);

    List<RoutePathEntity> getDepartures(int stationId);

    RoutePathEntity getFirstArc(int routeId);

    RoutePathEntity getLastArc(int routeId);

    List<Integer> getCommonRoutes(int startStationId, int finishStationId);

}
