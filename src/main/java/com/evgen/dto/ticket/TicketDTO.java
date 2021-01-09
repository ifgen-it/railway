package com.evgen.dto.ticket;

import com.evgen.dto.station.RouteDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.ticket.TicketEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketDTO implements Serializable {

    private int ticketId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime finishTime;

    private int seatNumber;

    private float price;

    private RouteDTO ticketRoute;

    private UserDTO user;

    private StationDTO startStation;

    private StationDTO finishStation;

    public static TicketDTO entityToDTO(TicketEntity entity){
        return new TicketDTO(entity.getTicketId(),
                entity.getStartTime(),
                entity.getFinishTime(),
                entity.getSeatNumber(),
                entity.getPrice(),
                RouteDTO.entityToDTO(entity.getTicketRoute()),
                UserDTO.entityToDTO(entity.getUser()),
                StationDTO.entityToDTO(entity.getStartStation()),
                StationDTO.entityToDTO(entity.getFinishStation()));
    }
    public static TicketEntity dtoToEntity(TicketDTO dto){
        return new TicketEntity(dto.getTicketId(),
                dto.getStartTime(),
                dto.getFinishTime(),
                dto.getSeatNumber(),
                dto.getPrice(),
                RouteDTO.dtoToEntity(dto.getTicketRoute()),
                UserDTO.dtoToEntity(dto.getUser()),
                StationDTO.dtoToEntity(dto.getStartStation()),
                StationDTO.dtoToEntity(dto.getFinishStation()));
    }

    public TicketDTO() {
    }

    public TicketDTO(int ticketId, LocalDateTime startTime, LocalDateTime finishTime, int seatNumber, float price,
                     RouteDTO ticketRoute, UserDTO user, StationDTO startStation, StationDTO finishStation) {
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
