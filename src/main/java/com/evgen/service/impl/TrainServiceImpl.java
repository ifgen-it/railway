package com.evgen.service.impl;

import com.evgen.dao.TrainDAO;
import com.evgen.dto.train.TrainDTO;
import com.evgen.entity.train.TrainEntity;
import com.evgen.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Override
    public List<TrainDTO> getAllTrains() {

        List<TrainDTO> dtos = new ArrayList<>();
        trainDAO.getAll().forEach(item -> dtos.add(TrainDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public int addTrain(TrainDTO train) {

        TrainEntity trainEntity = TrainDTO.dtoToEntity(train);

        return trainDAO.add(trainEntity);
    }

    @Override
    public TrainDTO getTrain(int trainId) {

        return TrainDTO.entityToDTO(trainDAO.get(trainId));
    }

    @Override
    public TrainDTO getByName(String trainName) {

        TrainEntity trainEntity =  trainDAO.getByName(trainName);

        if (trainEntity == null)
            return  null;

        TrainDTO trainDTO = TrainDTO.entityToDTO(trainEntity);
        return trainDTO;
    }

    @Override
    public List<Integer> getFreeTrains(LocalDateTime startTime, LocalDateTime finishTime) {

        return trainDAO.getFreeTrains(startTime, finishTime);
    }
}
