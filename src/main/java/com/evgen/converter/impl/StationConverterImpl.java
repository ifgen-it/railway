package com.evgen.converter.impl;

import com.evgen.converter.StationConverter;
import com.evgen.dto.station.RoutePathDTO;
import com.evgen.dto.station.RoutePathSimpleDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.station.StationSimpleDTO;
import org.springframework.stereotype.Service;

@Service
public class StationConverterImpl implements StationConverter {

    public RoutePathSimpleDTO convertRoutePathToSimple(RoutePathDTO routePath){

        RoutePathSimpleDTO routePathSimple = new RoutePathSimpleDTO();

        routePathSimple.setRoutePathId(routePath.getRoutePathId());
        routePathSimple.setArrivalTime(routePath.getArrivalTime());
        routePathSimple.setDepartureTime(routePath.getDepartureTime());
        routePathSimple.setRouteId(routePath.getRoute().getRouteId());
        routePathSimple.setRouteName(routePath.getRoute().getRouteName());
        routePathSimple.setTrainId(routePath.getRoute().getTrain().getTrainId());
        routePathSimple.setTrainName(routePath.getRoute().getTrain().getTrainName());

        return routePathSimple;
    }

    @Override
    public StationSimpleDTO convertStationToSimple(StationDTO station) {

        StationSimpleDTO stationSimple = new StationSimpleDTO();
        stationSimple.setStationId(station.getStationId());
        stationSimple.setStationName(station.getStationName());

        return stationSimple;
    }

}
