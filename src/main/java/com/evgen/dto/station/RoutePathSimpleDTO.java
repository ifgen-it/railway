package com.evgen.dto.station;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RoutePathSimpleDTO implements Serializable {

    private int routePathId;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private int routeId;

    private String routeName;

    private int trainId;

    private String trainName;

    public RoutePathSimpleDTO() {
    }

    public RoutePathSimpleDTO(int routePathId, LocalDateTime departureTime, LocalDateTime arrivalTime, int routeId, String routeName, int trainId, String trainName) {
        this.routePathId = routePathId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeId = routeId;
        this.routeName = routeName;
        this.trainId = trainId;
        this.trainName = trainName;
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

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    @Override
    public String toString() {
        return "RoutePathSimpleDTO{" +
                "routePathId=" + routePathId +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                '}';
    }
}
