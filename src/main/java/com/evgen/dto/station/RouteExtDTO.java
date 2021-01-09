package com.evgen.dto.station;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RouteExtDTO implements Serializable{

    public static float PRICE_FACTOR = 5;

    private RouteDTO routeDTO;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime routeDepartureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime routeArrivalTime;

    private StationDTO routeBeginStation;

    private StationDTO routeEndStation;

    private int routeLength;

    private float routePrice;

    public RouteExtDTO() {
    }

    public RouteExtDTO(RouteDTO routeDTO, LocalDateTime routeDepartureTime, LocalDateTime routeArrivalTime, StationDTO routeBeginStation, StationDTO routeEndStation, int routeLength) {
        this.routeDTO = routeDTO;
        this.routeDepartureTime = routeDepartureTime;
        this.routeArrivalTime = routeArrivalTime;
        this.routeBeginStation = routeBeginStation;
        this.routeEndStation = routeEndStation;
        this.routeLength = routeLength;
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

    @Override
    public String toString() {
        return "RouteExtDTO{" +
                "routeDTO=" + routeDTO.getRouteName() +
                ", routeDepartureTime=" + routeDepartureTime +
                ", routeArrivalTime=" + routeArrivalTime +
                ", routeBeginStation=" + routeBeginStation.getStationName() +
                ", routeEndStation=" + routeEndStation.getStationName() +
                ", routeLength=" + routeLength +
                '}';
    }

    public int getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public float getRoutePrice() {
        return routePrice;
    }

    public static float makePrice(int length){
        return length * PRICE_FACTOR;
    }


    public void setRoutePrice(float routePrice) {
        this.routePrice = routePrice;
    }
}
