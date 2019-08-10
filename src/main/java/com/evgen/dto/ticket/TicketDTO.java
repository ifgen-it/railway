package com.evgen.dto.ticket;

import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.user.UserDTO;
import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketDTO implements Serializable {

    private int ticketId;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private int seatNumber;

    private float price;

    private RouteDTO ticketRoute;

    private UserDTO user;

    private StationDTO startStation;

    private StationDTO finishStation;

    public TicketDTO() {
    }

    public TicketDTO(LocalDateTime startTime, LocalDateTime finishTime, int seatNumber, float price, RouteDTO ticketRoute, UserDTO user, StationDTO startStation, StationDTO finishStation) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.seatNumber = seatNumber;
        this.price = price;
        this.ticketRoute = ticketRoute;
        this.user = user;
        this.startStation = startStation;
        this.finishStation = finishStation;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RouteDTO getTicketRoute() {
        return ticketRoute;
    }

    public void setTicketRoute(RouteDTO ticketRoute) {
        this.ticketRoute = ticketRoute;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public StationDTO getStartStation() {
        return startStation;
    }

    public void setStartStation(StationDTO startStation) {
        this.startStation = startStation;
    }

    public StationDTO getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(StationDTO finishStation) {
        this.finishStation = finishStation;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketId=" + ticketId +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", seatNumber=" + seatNumber +
                ", price=" + price +
                ", ticketRoute=" + ticketRoute.getRouteName() +
                ", user=" + user +
                ", startStation=" + startStation +
                ", finishStation=" + finishStation +
                '}';
    }
}
