package com.evgen.dto.train;

import com.evgen.entity.train.TrainEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainDTO implements Serializable {

    private int trainId;

    private String trainName;

    private int seatsAmount;

    private List<Integer> routes;

    public static TrainDTO entityToDTO(TrainEntity entity){
        TrainDTO dto = new TrainDTO(entity.getTrainId(),
                entity.getTrainName(),
                entity.getSeatsAmount());

        List<Integer> routes = new ArrayList<>();
        entity.getRoutes().forEach(r -> routes.add(r.getRouteId()));
        dto.setRoutes(routes);
        return dto;
    }
    public static TrainEntity dtoToEntity(TrainDTO dto){
        return new TrainEntity(dto.getTrainId(), dto.getTrainName(), dto.getSeatsAmount());
    }

    public TrainDTO() {
    }

    public TrainDTO(int trainId, String trainName, int seatsAmount) {
        this.trainId = trainId;
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

    public List<Integer> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Integer> routes) {
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
