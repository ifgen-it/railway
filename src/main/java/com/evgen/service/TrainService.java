package com.evgen.service;

import com.evgen.dto.train.TrainDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainService {

    List<TrainDTO> getAllTrains(); //++

    int addTrain(TrainDTO train); //++

    TrainDTO getTrain(int trainId); //++

    TrainDTO getByName(String trainName); //++

    List<Integer> getFreeTrains(LocalDateTime startTime, LocalDateTime finishTime); //++


}
