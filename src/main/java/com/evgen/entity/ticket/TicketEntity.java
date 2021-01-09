package com.evgen.entity.ticket;

import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.user.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class TicketEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private int ticketId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "finish_time", nullable = false)
    private LocalDateTime finishTime;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Column(name = "price", nullable = false)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private RouteEntity ticketRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_station", nullable = false)
    private StationEntity startStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finish_station", nullable = false)
    private StationEntity finishStation;


    public TicketEntity() {
    }

    public TicketEntity(int ticketId, LocalDateTime startTime, LocalDateTime finishTime, int seatNumber, float price,
                        RouteEntity ticketRoute, UserEntity user, StationEntity startStation, StationEntity finishStation) {
        this.ticketId = ticketId;
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

    public RouteEntity getTicketRoute() {
        return ticketRoute;
    }

    public void setTicketRoute(RouteEntity ticketRoute) {
        this.ticketRoute = ticketRoute;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public StationEntity getStartStation() {
        return startStation;
    }

    public void setStartStation(StationEntity startStation) {
        this.startStation = startStation;
    }

    public StationEntity getFinishStation() {
        return finishStation;
    }

    public void setFinishStation(StationEntity finishStation) {
        this.finishStation = finishStation;
    }
}
