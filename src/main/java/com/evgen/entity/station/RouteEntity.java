package com.evgen.entity.station;

import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.train.TrainEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "route")
public class RouteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", nullable = false)
    private int routeId;

    @Column(name = "route_name", length = 60, nullable = false)
    private String routeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", nullable = false)
    private TrainEntity train;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<RoutePathEntity> routePaths = new ArrayList<>();

    @OneToMany(mappedBy = "ticketRoute", fetch = FetchType.LAZY)
    private List<TicketEntity> routeTickets = new ArrayList<>();

    public RouteEntity() {
    }

    public RouteEntity(String routeName, TrainEntity train) {
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

    public TrainEntity getTrain() {
        return train;
    }

    public void setTrain(TrainEntity train) {
        this.train = train;
    }

    public List<RoutePathEntity> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<RoutePathEntity> routePaths) {
        this.routePaths = routePaths;
    }

    public List<TicketEntity> getRouteTickets() {
        return routeTickets;
    }

    public void setRouteTickets(List<TicketEntity> routeTickets) {
        this.routeTickets = routeTickets;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouteEntity)) return false;
        RouteEntity that = (RouteEntity) o;
        return getRouteId() == that.getRouteId() &&
                getRouteName().equals(that.getRouteName()) &&
                getTrain().equals(that.getTrain());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRouteId(), getRouteName(), getTrain());
    }
}
