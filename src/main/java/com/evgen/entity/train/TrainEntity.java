package com.evgen.entity.train;

import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "train")
public class TrainEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", nullable = false)
    private int trainId;

    @Column(name = "train_name", length = 60, nullable = false, unique = true)
    private String trainName;

    @Column(name = "seats_amount", nullable = false)
    private int seatsAmount;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY)
    private List<RouteEntity> routes = new ArrayList<>();


    public TrainEntity() {
    }

    public TrainEntity(int trainId, String trainName, int seatsAmount) {
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

    public List<RouteEntity> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteEntity> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainEntity)) return false;
        TrainEntity that = (TrainEntity) o;
        return getTrainId() == that.getTrainId() &&
                getSeatsAmount() == that.getSeatsAmount() &&
                getTrainName().equals(that.getTrainName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainId(), getTrainName(), getSeatsAmount());
    }
}

