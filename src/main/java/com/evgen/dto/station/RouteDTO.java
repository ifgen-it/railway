package com.evgen.dto.station;

import com.evgen.dto.train.TrainDTO;
import com.evgen.entity.station.RouteEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RouteDTO implements Serializable {

    private int routeId;

    private String routeName;

    private TrainDTO train;

    private List<Integer> routePaths;

    private List<Integer> routeTickets;

    public static RouteDTO entityToDTO(RouteEntity entity){
        RouteDTO dto = new RouteDTO(entity.getRouteId(),
                entity.getRouteName(),
                TrainDTO.entityToDTO(entity.getTrain()));

        List<Integer> routePaths = new ArrayList<>();
        entity.getRoutePaths().forEach(r -> routePaths.add(r.getRoutePathId()));
        dto.setRoutePaths(routePaths);

        List<Integer> routeTickets = new ArrayList<>();
        entity.getRouteTickets().forEach(r -> routeTickets.add(r.getTicketId()));
        dto.setRouteTickets(routeTickets);

        return dto;
    }
    public static RouteEntity dtoToEntity(RouteDTO dto){
        return new RouteEntity(dto.getRouteId(),
                dto.getRouteName(),
                TrainDTO.dtoToEntity(dto.getTrain()));
    }

    public RouteDTO() {
    }

    public RouteDTO(int routeId, String routeName, TrainDTO train) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.train = train;
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

    public TrainDTO getTrain() {
        return train;
    }

    public void setTrain(TrainDTO train) {
        this.train = train;
    }

    public List<Integer> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<Integer> routePaths) {
        this.routePaths = routePaths;
    }

    public List<Integer> getRouteTickets() {
        return routeTickets;
    }

    public void setRouteTickets(List<Integer> routeTickets) {
        this.routeTickets = routeTickets;
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
                "routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", train=" + train +
                '}';
    }
}
