package com.evgen.service;

import com.evgen.dto.train.TrainDTO;

import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrains();

    int addTrain(TrainDTO train);

    TrainDTO getTrain(int trainId);


}
