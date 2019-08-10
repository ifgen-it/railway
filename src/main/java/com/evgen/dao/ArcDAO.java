package com.evgen.dao;

import com.evgen.entity.station.ArcEntity;

import java.util.List;

public interface ArcDAO {

    List<ArcEntity> getAll();

    int add(ArcEntity arc);

    ArcEntity get(int id);

    void remove(ArcEntity arc);

    void removeWith(int id);

    ArcEntity update(ArcEntity arc);
}
