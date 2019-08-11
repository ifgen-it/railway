package com.evgen.service.impl;

import com.evgen.dao.ArcDAO;
import com.evgen.dao.RouteDAO;
import com.evgen.dao.RoutePathDAO;
import com.evgen.dao.StationDAO;
import com.evgen.dto.station.*;
import com.evgen.entity.station.ArcEntity;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.RoutePathEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private ArcDAO arcDAO;

    @Autowired
    private RouteDAO routeDAO;

    @Autowired
    private RoutePathDAO routePathDAO;

    @Override
    public List<StationDTO> getAllStations() {

        List<StationDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        stationDAO.getAll().forEach(item -> dtos.add(modelMapper.map(item, StationDTO.class)));

        return dtos;
    }

    @Override
    public List<ArcDTO> getAllArcs() {

        List<ArcDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        arcDAO.getAll().forEach(item -> dtos.add(modelMapper.map(item, ArcDTO.class)));

        return dtos;
    }

    @Override
    public List<RouteDTO> getAllRoutes() {

        List<RouteDTO> dtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        routeDAO.getAll().forEach(item -> dtos.add(modelMapper.map(item, RouteDTO.class)));

        return dtos;
    }

    @Override
    public StationDTO getStation(int stationId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(stationDAO.get(stationId), StationDTO.class);
    }

    @Override
    public ArcDTO getArc(int arcId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(arcDAO.get(arcId), ArcDTO.class);
    }

    @Override
    public RouteDTO getRoute(int routeId) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(routeDAO.get(routeId), RouteDTO.class);
    }

    @Override
    public int addStation(StationDTO station) {

        ModelMapper modelMapper = new ModelMapper();
        StationEntity stationEntity = modelMapper.map(station, StationEntity.class);

        return stationDAO.add(stationEntity);
    }

    @Override
    public int addRoute(RouteDTO route) {

        ModelMapper modelMapper = new ModelMapper();
        RouteEntity routeEntity = modelMapper.map(route, RouteEntity.class);

        return routeDAO.add(routeEntity);
    }

    @Override
    public int addArc(ArcDTO arc) {

        ModelMapper modelMapper = new ModelMapper();
        ArcEntity arcEntity = modelMapper.map(arc, ArcEntity.class);

        return arcDAO.add(arcEntity);
    }

    @Override
    public int addRoutePath(RoutePathDTO routePath) {

        ModelMapper modelMapper = new ModelMapper();
        RoutePathEntity routePathEntity = modelMapper.map(routePath, RoutePathEntity.class);

        return routePathDAO.add(routePathEntity);
    }

    @Override
    public List<RoutePathDTO> getArrivals(int stationId) {

        ModelMapper modelMapper = new ModelMapper();
        List<RoutePathDTO> dtos = new ArrayList<>();

        List<RoutePathEntity> entities = routePathDAO.getArrivals(stationId);
        entities.forEach(item -> dtos.add(modelMapper.map(item, RoutePathDTO.class)));

        return dtos;
    }

    @Override
    public List<RoutePathDTO> getDepartures(int stationId) {
        ModelMapper modelMapper = new ModelMapper();
        List<RoutePathDTO> dtos = new ArrayList<>();

        List<RoutePathEntity> entities = routePathDAO.getDepartures(stationId);
        entities.forEach(item -> dtos.add(modelMapper.map(item, RoutePathDTO.class)));

        return dtos;
    }

    @Override
    public StationDTO getByName(String stationName) {

        StationEntity stationEntity = stationDAO.getByName(stationName);
        ModelMapper modelMapper = new ModelMapper();

        if (stationEntity == null)
            return null;

        StationDTO stationDTO = modelMapper.map(stationEntity, StationDTO.class);
        return stationDTO;
    }

    @Override
    public ArcDTO getArcByStations(int beginStationId, int endStationId) {

        ArcEntity arcEntity = arcDAO.getByStations(beginStationId, endStationId);
        ModelMapper modelMapper = new ModelMapper();

        if (arcEntity == null)
            return null;

        ArcDTO arcDTO = modelMapper.map(arcEntity, ArcDTO.class);
        return arcDTO;
    }

    @Override
    public RoutePathDTO getFirstArc(int routeId) {

        RoutePathEntity entity = routePathDAO.getFirstArc(routeId);
        ModelMapper modelMapper = new ModelMapper();

        if (entity == null)
            return null;

        RoutePathDTO dto = modelMapper.map(entity, RoutePathDTO.class);
        return dto;
    }

    @Override
    public RoutePathDTO getLastArc(int routeId) {

        RoutePathEntity entity = routePathDAO.getLastArc(routeId);
        ModelMapper modelMapper = new ModelMapper();

        if (entity == null)
            return null;

        RoutePathDTO dto = modelMapper.map(entity, RoutePathDTO.class);
        return dto;
    }

    @Override
    public List<RouteExtDTO> getAllRoutesExt() {

        List<RouteExtDTO> dtosExt = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();


        routeDAO.getAll().forEach(item -> {

            RouteDTO routeDTO = modelMapper.map(item, RouteDTO.class);
            RouteExtDTO routeExtDTO = new RouteExtDTO();

            routeExtDTO.setRouteDTO(routeDTO);

            RoutePathEntity firstArcEntity = routePathDAO.getFirstArc(routeDTO.getRouteId());
            RoutePathEntity lastArcEntity = routePathDAO.getLastArc(routeDTO.getRouteId());
            RoutePathDTO firstArc = modelMapper.map(firstArcEntity, RoutePathDTO.class);
            RoutePathDTO lastArc = modelMapper.map(lastArcEntity, RoutePathDTO.class);

            routeExtDTO.setRouteDepartureTime(firstArc.getDepartureTime());
            routeExtDTO.setRouteArrivalTime(lastArc.getArrivalTime());

            routeExtDTO.setRouteBeginStation(firstArc.getArc().getBeginStation());
            routeExtDTO.setRouteEndStation(lastArc.getArc().getEndStation());

            dtosExt.add(routeExtDTO);

        });

        return dtosExt;
    }

    @Override
    public List<Integer> getCommonRoutes(int startStationId, int finishStationId) {

        List<Integer> routes = routePathDAO.getCommonRoutes(startStationId, finishStationId);

        return routes;
    }

    @Override
    public RouteExtDTO getRouteExt(int routeId) {

        ModelMapper modelMapper = new ModelMapper();

        RouteExtDTO routeExtDTO = new RouteExtDTO();
        routeExtDTO.setRouteDTO(getRoute(routeId));

        routeExtDTO.setRouteDepartureTime(routePathDAO.getFirstArc(routeId).getDepartureTime());
        routeExtDTO.setRouteArrivalTime(routePathDAO.getLastArc(routeId).getArrivalTime());

        routeExtDTO.setRouteBeginStation(
                modelMapper.map(
                        routePathDAO.getFirstArc(routeId).getArc().getBeginStation(),
                        StationDTO.class
                )
        );

        routeExtDTO.setRouteEndStation(
                modelMapper.map(
                        routePathDAO.getLastArc(routeId).getArc().getEndStation(),
                        StationDTO.class
                )
        );

        return routeExtDTO;
    }
}