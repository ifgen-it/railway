package com.evgen.service;

import com.evgen.dao.TrainDAO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.train.TrainDTO;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.train.TrainEntity;
import com.evgen.service.impl.TrainServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainServiceTest {

    @InjectMocks
    private TrainServiceImpl trainService;

    @Mock
    private TrainDAO trainDAO;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTrainsTest(){

        when(trainDAO.getAll()).thenReturn(new ArrayList<TrainEntity>());
        List<TrainDTO> trainDTOS = trainService.getAllTrains();
        assertNotNull(trainDTOS);
    }

    @Test
    public void getTrainTest(){
        int trainId = 1;
        when(trainDAO.get(trainId)).thenReturn(new TrainEntity());
        TrainDTO trainDTO = trainService.getTrain(trainId);
        assertNotNull(trainDTO);
    }


}
