package com.evgen.dto.station;

import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.train.TrainDTO;
import java.io.Serializable;
import java.util.List;

public class RouteDTO implements Serializable {

    private int routeId;

    private String routeName;

    private TrainDTO train;

    private List<RoutePathDTO> routePaths;

    private List<TicketDTO> routeTickets;

    public RouteDTO() {
    }

    public RouteDTO(String routeName, TrainDTO train) {

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

    public List<RoutePathDTO> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<RoutePathDTO> routePaths) {
        this.routePaths = routePaths;
    }

    public List<TicketDTO> getRouteTickets() {
        return routeTickets;
    }

    public void setRouteTickets(List<TicketDTO> routeTickets) {
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
