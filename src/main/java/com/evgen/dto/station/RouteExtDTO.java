package com.evgen.dto.station;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RouteExtDTO implements Serializable{

    private RouteDTO routeDTO;

    private LocalDateTime routeDepartureTime;

    private LocalDateTime routeArrivalTime;

    private StationDTO routeBeginStation;

    private StationDTO routeEndStation;

    public RouteExtDTO() {
    }

    public RouteExtDTO(RouteDTO routeDTO, LocalDateTime routeDepartureTime, LocalDateTime routeArrivalTime, StationDTO routeBeginStation, StationDTO routeEndStation) {
        this.routeDTO = routeDTO;
        this.routeDepartureTime = routeDepartureTime;
        this.routeArrivalTime = routeArrivalTime;
        this.routeBeginStation = routeBeginStation;
        this.routeEndStation = routeEndStation;
    }

    public RouteDTO getRouteDTO() {
        return routeDTO;
    }

    public void setRouteDTO(RouteDTO routeDTO) {
        this.routeDTO = routeDTO;
    }

    public LocalDateTime getRouteDepartureTime() {
        return routeDepartureTime;
    }

    public void setRouteDepartureTime(LocalDateTime routeDepartureTime) {
        this.routeDepartureTime = routeDepartureTime;
    }

    public LocalDateTime getRouteArrivalTime() {
        return routeArrivalTime;
    }

    public void setRouteArrivalTime(LocalDateTime routeArrivalTime) {
        this.routeArrivalTime = routeArrivalTime;
    }

    public StationDTO getRouteBeginStation() {
        return routeBeginStation;
    }

    public void setRouteBeginStation(StationDTO routeBeginStation) {
        this.routeBeginStation = routeBeginStation;
    }

    public StationDTO getRouteEndStation() {
        return routeEndStation;
    }

    public void setRouteEndStation(StationDTO routeEndStation) {
        this.routeEndStation = routeEndStation;
    }
}
