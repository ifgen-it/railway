package com.evgen.dto.station;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RoutePathDTO implements Serializable {

    private int routePathId;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private RouteDTO route;

    private ArcDTO arc;

    public RoutePathDTO(LocalDateTime departureTime, LocalDateTime arrivalTime, RouteDTO route, ArcDTO arc) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.route = route;
        this.arc = arc;
    }

    public RoutePathDTO() {
    }

    public int getRoutePathId() {
        return routePathId;
    }

    public void setRoutePathId(int routePathId) {
        this.routePathId = routePathId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public ArcDTO getArc() {
        return arc;
    }

    public void setArc(ArcDTO arc) {
        this.arc = arc;
    }

    @Override
    public String toString() {
        return "RoutePathDTO{" +
                "routePathId=" + routePathId +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", route=" + route +
                ", arc=" + arc +
                '}';
    }
}
