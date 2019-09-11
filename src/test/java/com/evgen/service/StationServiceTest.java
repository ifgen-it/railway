package com.evgen.service;

import com.evgen.dao.ArcDAO;
import com.evgen.dao.RouteDAO;
import com.evgen.dao.RoutePathDAO;
import com.evgen.dao.StationDAO;
import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.impl.StationServiceImpl;
import com.evgen.service.impl.UserServiceImpl;
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
public class StationServiceTest {

    @InjectMocks
    private StationServiceImpl stationService;

    @Mock
    private StationDAO stationDAO;

    @Mock
    private ArcDAO arcDAO;

    @Mock
    private RouteDAO routeDAO;

    @Mock
    private RoutePathDAO routePathDAO;

    @Mock
    private TrainService trainService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStationsTest(){

        when(stationDAO.getAll()).thenReturn(new ArrayList<StationEntity>());
        List<StationDTO> stationDTOS = stationService.getAllStations();
        assertNotNull(stationDTOS);
    }

    @Test
    public void getStationTest(){
        int stationId = 1;
        when(stationDAO.get(stationId)).thenReturn(new StationEntity());
        StationDTO stationDTO = stationService.getStation(stationId);
        assertNotNull(stationDTO);
    }

    @Test
    public void getAllRoutesTest(){

        when(routeDAO.getAll()).thenReturn(new ArrayList<RouteEntity>());
        List<RouteDTO> routeDTOS = stationService.getAllRoutes();
        assertNotNull(routeDTOS);
    }

}
