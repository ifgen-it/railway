package com.evgen.entity.station;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "routepath")
public class RoutePathEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_path_id", nullable = false)
    private int routePathId;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private RouteEntity route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arc_id", nullable = false)
    private ArcEntity arc;

    public RoutePathEntity() {
    }

    public RoutePathEntity(LocalDateTime departureTime, LocalDateTime arrivalTime, RouteEntity route, ArcEntity arc) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.route = route;
        this.arc = arc;
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

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public ArcEntity getArc() {
        return arc;
    }

    public void setArc(ArcEntity arc) {
        this.arc = arc;
    }

    @Override
    public String toString() {
        return "RoutePathEntity{" +
                "routePathId=" + routePathId +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", route=" + route +
                ", arc=" + arc +
                '}';
    }
}
