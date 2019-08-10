package com.evgen.dao;

import com.evgen.entity.station.StationEntity;

import java.util.List;

public interface StationDAO {

    List<StationEntity> getAll();

    int add(StationEntity station);

    StationEntity get(int id);

    void remove(StationEntity station);

    void removeWith(int id);

    StationEntity update(StationEntity station);
}
