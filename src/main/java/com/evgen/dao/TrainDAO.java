package com.evgen.dao;

import com.evgen.entity.train.TrainEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainDAO {

    List<TrainEntity> getAll();

    int add(TrainEntity train);

    TrainEntity get(int id);

    void remove(TrainEntity train);

    void removeWith(int id);

    TrainEntity update(TrainEntity train);

    TrainEntity getByName(String trainName);

    List<Integer> getFreeTrains(LocalDateTime startTime, LocalDateTime finishTime);
}
