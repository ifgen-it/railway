package com.evgen.service;

import com.evgen.dto.station.*;

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

}
