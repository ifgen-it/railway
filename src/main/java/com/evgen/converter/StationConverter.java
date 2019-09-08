package com.evgen.converter;

import com.evgen.dto.station.RoutePathDTO;
import com.evgen.dto.station.RoutePathSimpleDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.station.StationSimpleDTO;

public interface StationConverter {

    RoutePathSimpleDTO convertRoutePathToSimple(RoutePathDTO routePath, boolean isArrival);

    StationSimpleDTO convertStationToSimple(StationDTO station);
}
