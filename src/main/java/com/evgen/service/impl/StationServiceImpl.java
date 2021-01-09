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
import com.evgen.service.TrainService;
import com.evgen.service.exception.UseReservedTrainException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private static final Logger logger = Logger.getLogger(StationServiceImpl.class);

    private StationDAO stationDAO;

    private ArcDAO arcDAO;

    private RouteDAO routeDAO;

    private RoutePathDAO routePathDAO;

    private TrainService trainService;

    public StationServiceImpl(StationDAO stationDAO,
                              ArcDAO arcDAO,
                              RouteDAO routeDAO,
                              RoutePathDAO routePathDAO,
                              @Qualifier("trainServiceRestClient") TrainService trainService) {
        this.stationDAO = stationDAO;
        this.arcDAO = arcDAO;
        this.routeDAO = routeDAO;
        this.routePathDAO = routePathDAO;
        this.trainService = trainService;
    }

    @Override
    public List<StationDTO> getAllStations() {

        List<StationDTO> dtos = new ArrayList<>();
        stationDAO.getAll().forEach(item -> dtos.add(StationDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public List<ArcDTO> getAllArcs() {

        List<ArcDTO> dtos = new ArrayList<>();
        arcDAO.getAll().forEach(item -> dtos.add(ArcDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public List<RouteDTO> getAllRoutes() {

        List<RouteDTO> dtos = new ArrayList<>();
        routeDAO.getAll().forEach(item -> dtos.add(RouteDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public StationDTO getStation(int stationId) {

        StationEntity stationEntity = stationDAO.get(stationId);
        if (stationEntity == null){
            return null;
        }
        return StationDTO.entityToDTO(stationEntity);
    }

    @Override
    public ArcDTO getArc(int arcId) {

        return ArcDTO.entityToDTO(arcDAO.get(arcId));
    }

    @Override
    public RouteDTO getRoute(int routeId) {

        return RouteDTO.entityToDTO(routeDAO.get(routeId));
    }

    @Override
    public int addStation(StationDTO station) {

        StationEntity stationEntity = StationDTO.dtoToEntity(station);

        return stationDAO.add(stationEntity);
    }

    @Override
    public int addRoute(RouteDTO route) {

        RouteEntity routeEntity = RouteDTO.dtoToEntity(route);

        return routeDAO.add(routeEntity);
    }

    @Override
    public int addArc(ArcDTO arc) {

        ArcEntity arcEntity = ArcDTO.dtoToEntity(arc);

        return arcDAO.add(arcEntity);
    }

    @Override
    public int addRoutePath(RoutePathDTO routePath) {

        RoutePathEntity routePathEntity = RoutePathDTO.dtoToEntity(routePath);

        return routePathDAO.add(routePathEntity);
    }

    @Override
    public List<RoutePathDTO> getArrivals(int stationId) {

        List<RoutePathDTO> dtos = new ArrayList<>();

        List<RoutePathEntity> entities = routePathDAO.getArrivals(stationId);
        entities.forEach(item -> dtos.add(RoutePathDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public List<RoutePathDTO> getDepartures(int stationId) {

        List<RoutePathDTO> dtos = new ArrayList<>();

        List<RoutePathEntity> entities = routePathDAO.getDepartures(stationId);
        entities.forEach(item -> dtos.add(RoutePathDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public StationDTO getByName(String stationName) {

        StationEntity stationEntity = stationDAO.getByName(stationName);

        if (stationEntity == null)
            return null;

        StationDTO stationDTO = StationDTO.entityToDTO(stationEntity);
        return stationDTO;
    }

    @Override
    public ArcDTO getArcByStations(int beginStationId, int endStationId) {

        ArcEntity arcEntity = arcDAO.getByStations(beginStationId, endStationId);

        if (arcEntity == null)
            return null;

        ArcDTO arcDTO = ArcDTO.entityToDTO(arcEntity);
        return arcDTO;
    }

    @Override
    public RoutePathDTO getFirstArc(int routeId) {

        RoutePathEntity entity = routePathDAO.getFirstArc(routeId);

        if (entity == null)
            return null;

        RoutePathDTO dto = RoutePathDTO.entityToDTO(entity);
        return dto;
    }

    @Override
    public RoutePathDTO getLastArc(int routeId) {

        RoutePathEntity entity = routePathDAO.getLastArc(routeId);

        if (entity == null)
            return null;

        RoutePathDTO dto = RoutePathDTO.entityToDTO(entity);
        return dto;
    }

    @Override
    public List<RouteExtDTO> getAllRoutesExt() {

        List<RouteExtDTO> dtosExt = new ArrayList<>();

        routeDAO.getAll().forEach(item -> {

            RouteDTO routeDTO = RouteDTO.entityToDTO(item);
            RouteExtDTO routeExtDTO = new RouteExtDTO();

            routeExtDTO.setRouteDTO(routeDTO);

            RoutePathEntity firstArcEntity = routePathDAO.getFirstArc(routeDTO.getRouteId());
            RoutePathEntity lastArcEntity = routePathDAO.getLastArc(routeDTO.getRouteId());
            RoutePathDTO firstArc = RoutePathDTO.entityToDTO(firstArcEntity);
            RoutePathDTO lastArc = RoutePathDTO.entityToDTO(lastArcEntity);

            routeExtDTO.setRouteDepartureTime(firstArc.getDepartureTime());
            routeExtDTO.setRouteArrivalTime(lastArc.getArrivalTime());

            routeExtDTO.setRouteBeginStation(firstArc.getArc().getBeginStation());
            routeExtDTO.setRouteEndStation(lastArc.getArc().getEndStation());

            dtosExt.add(routeExtDTO);

        });

        logger.info("Get all routes extended");

        return dtosExt;
    }

    @Override
    public List<Integer> getCommonRoutes(int startStationId, int finishStationId, Date dateFrom, Date dateTo) {

        List<Integer> routes = routePathDAO.getCommonRoutes(startStationId, finishStationId, dateFrom, dateTo);

        return routes;
    }

    @Override
    public RouteExtDTO getRouteExt(int routeId) {


        RouteExtDTO routeExtDTO = new RouteExtDTO();
        routeExtDTO.setRouteDTO(getRoute(routeId));

        routeExtDTO.setRouteDepartureTime(routePathDAO.getFirstArc(routeId).getDepartureTime());
        routeExtDTO.setRouteArrivalTime(routePathDAO.getLastArc(routeId).getArrivalTime());

        routeExtDTO.setRouteBeginStation(
                StationDTO.entityToDTO(routePathDAO.getFirstArc(routeId).getArc().getBeginStation())

        );

        routeExtDTO.setRouteEndStation(
                StationDTO.entityToDTO(routePathDAO.getLastArc(routeId).getArc().getEndStation())
        );

        return routeExtDTO;
    }

    @Override
    public LocalDateTime getRouteStartTime(int routeId, int startStationId) {
        return routePathDAO.getRouteStartTime(routeId, startStationId);
    }

    @Override
    public LocalDateTime getRouteFinishTime(int routeId, int finishStationId) {
        return routePathDAO.getRouteFinishTime(routeId, finishStationId);
    }

    @Override
    public Integer getRouteLength(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {
        return routePathDAO.getRouteLength(routeId, startTime, finishTime);
    }

    @Override
    public List<ArcDTO> getOutArcs(int stationId) {

        List<ArcDTO> dtos = new ArrayList<>();

        List<ArcEntity> entities = arcDAO.getOutArcs(stationId);
        entities.forEach(item -> dtos.add(ArcDTO.entityToDTO(item)));

        return dtos;
    }

    @Override
    public int createRoute(RouteDTO routeDTO, List<RoutePathDTO> routePathDTOS) throws UseReservedTrainException {

        RouteEntity routeEntity = RouteDTO.dtoToEntity(routeDTO);

        // BEFORE CREATION ROUTE WITH SELECTED TRAIN ID
        // NEED TO CHECK THIS TRAIN ID - IF SOME MANAGER USED IT
        // IN OUR TIME RANGE (DEPARTURE - ARRIVAL)

        RoutePathDTO firstRoutePath = routePathDTOS.get(0);
        RoutePathDTO lastRoutePath = routePathDTOS.get(routePathDTOS.size() - 1);

        LocalDateTime departureTime = firstRoutePath.getDepartureTime();
        LocalDateTime arrivalTime = lastRoutePath.getArrivalTime();

        List<Integer> freeTrainsId = trainService.getFreeTrains(departureTime, arrivalTime);
        int trainId = routeDTO.getTrain().getTrainId();



        if (freeTrainsId.contains(Integer.valueOf(trainId)) == false){

            logger.warn("UseReservedTrainException: Train : " + trainId + "_" + routeDTO.getTrain().getTrainName() +" was used in another route recently");
            throw new UseReservedTrainException("Train : " + trainId + "_" + routeDTO.getTrain().getTrainName() +
                    " was used in another route recently");
        }


        // ALL RIGHT - ADD ROUTE

        int routeId = routeDAO.add(routeEntity);
        RouteEntity realRouteEntity = routeDAO.get(routeId);
        RouteDTO realRouteDTO = RouteDTO.entityToDTO(realRouteEntity);

        routePathDTOS.forEach(item -> item.setRoute(realRouteDTO));

        List<RoutePathEntity> routePathEntities = new ArrayList<>();
        routePathDTOS.forEach(item ->routePathEntities.add(RoutePathDTO.dtoToEntity(item)));

        routePathEntities.forEach(item -> routePathDAO.add(item));

        logger.info("Route created, routeId = " + routeId);
        return routeId;
    }

    @Override
    public List<String> getStationNames(int routeId) {

        return stationDAO.getStationNames(routeId);
    }
}
