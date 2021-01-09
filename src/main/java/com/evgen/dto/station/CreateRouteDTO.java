package com.evgen.dto.station;

import java.io.Serializable;
import java.util.List;

public class CreateRouteDTO implements Serializable {

    private RouteDTO routeDTO;

    private List<RoutePathDTO> routePathDTOS;

    public CreateRouteDTO() {
    }

    public CreateRouteDTO(RouteDTO routeDTO, List<RoutePathDTO> routePathDTOS) {
        this.routeDTO = routeDTO;
        this.routePathDTOS = routePathDTOS;
    }

    public RouteDTO getRouteDTO() {
        return routeDTO;
    }

    public void setRouteDTO(RouteDTO routeDTO) {
        this.routeDTO = routeDTO;
    }

    public List<RoutePathDTO> getRoutePathDTOS() {
        return routePathDTOS;
    }

    public void setRoutePathDTOS(List<RoutePathDTO> routePathDTOS) {
        this.routePathDTOS = routePathDTOS;
    }


}
