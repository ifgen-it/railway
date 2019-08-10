package com.evgen.dto.train;

import com.evgen.dto.station.RouteDTO;

import java.io.Serializable;
import java.util.List;

public class TrainDTO implements Serializable {

    private int trainId;

    private String trainName;

    private int seatsAmount;

    private List<RouteDTO> routes;

    public TrainDTO() {
    }

    public TrainDTO(String trainName, int seatsAmount) {

        this.trainName = trainName;
        this.seatsAmount = seatsAmount;
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

    public int getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(int seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDTO> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "trainId=" + trainId +
                ", trainName='" + trainName + '\'' +
                '}';
    }
}
