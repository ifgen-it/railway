package com.evgen.dao;

import com.evgen.entity.train.TrainEntity;

import java.util.List;

public interface TrainDAO {

    List<TrainEntity> getAll();

    int add(TrainEntity train);

    TrainEntity get(int id);

    void remove(TrainEntity train);

    void removeWith(int id);

    TrainEntity update(TrainEntity train);
}
