package com.evgen.dto.station;

import com.evgen.entity.station.RoutePathEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RoutePathDTO implements Serializable {

    private int routePathId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime departureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime arrivalTime;

    private RouteDTO route;

    private ArcDTO arc;

    public static RoutePathDTO entityToDTO(RoutePathEntity entity){
        RoutePathDTO dto = new RoutePathDTO(entity.getRoutePathId(),
                entity.getDepartureTime(),
                entity.getArrivalTime(),
                RouteDTO.entityToDTO(entity.getRoute()),
                ArcDTO.entityToDTO(entity.getArc()));

        return dto;
    }
    public static RoutePathEntity dtoToEntity(RoutePathDTO dto){
        return new RoutePathEntity(dto.getRoutePathId(),
                dto.getDepartureTime(),
                dto.getArrivalTime(),
                RouteDTO.dtoToEntity(dto.getRoute()),
                ArcDTO.dtoToEntity(dto.getArc()));
    }

    public RoutePathDTO(int routePathId, LocalDateTime departureTime, LocalDateTime arrivalTime, RouteDTO route, ArcDTO arc) {
        this.routePathId = routePathId;
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
                ", routeId=" + route.getRouteId() +
                ", arcId=" + arc.getArcId() +
                '}';
    }
}
