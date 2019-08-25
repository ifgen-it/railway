package com.evgen.service;

import com.evgen.dto.station.*;
import com.evgen.service.exception.UseReservedTrainException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface StationService {

    List<StationDTO> getAllStations();

    List<ArcDTO> getAllArcs();

    List<RouteDTO> getAllRoutes();

    StationDTO getStation(int stationId);

    ArcDTO getArc(int arcId);

    RouteDTO getRoute(int routeId);

    int addStation(StationDTO station);

    int addRoute(RouteDTO route);

    int addArc(ArcDTO arc);

    int addRoutePath(RoutePathDTO routePath);

    List<RoutePathDTO> getArrivals(int stationId);

    List<RoutePathDTO> getDepartures(int stationId);

    StationDTO getByName(String stationName);

    ArcDTO getArcByStations(int beginStationId, int endStationId);

    RoutePathDTO getFirstArc(int routeId);

    RoutePathDTO getLastArc(int routeId);

    List<RouteExtDTO> getAllRoutesExt();

    List<Integer> getCommonRoutes(int startStationId, int finishStationId, Date dateFrom, Date dateTo);

    RouteExtDTO getRouteExt(int routeId);

    LocalDateTime getRouteStartTime(int routeId, int startStationId);

    LocalDateTime getRouteFinishTime(int routeId, int finishStationId);

    Integer getRouteLength(int routeId, LocalDateTime startTime, LocalDateTime finishTime);

    List<ArcDTO> getOutArcs(int stationId);

    int createRoute(RouteDTO routeDTO, List<RoutePathDTO> routePathDTOS) throws UseReservedTrainException;
}
