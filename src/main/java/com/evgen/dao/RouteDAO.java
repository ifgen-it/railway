package com.evgen.dao;

import com.evgen.entity.station.RouteEntity;

import java.util.List;

public interface RouteDAO {

    List<RouteEntity> getAll();

    int add(RouteEntity route);

    RouteEntity get(int id);

    void remove(RouteEntity route);

    void removeWith(int id);

    RouteEntity update(RouteEntity route);
}
