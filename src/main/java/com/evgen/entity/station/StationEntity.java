package com.evgen.entity.station;

import com.evgen.entity.ticket.TicketEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station")
public class StationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private int stationId;

    @Column(name = "station_name", length = 100, nullable = false, unique = true)
    private String stationName;

    @OneToMany(mappedBy = "beginStation", fetch = FetchType.LAZY)
    private List<ArcEntity> beginStationArcs = new ArrayList<>();

    @OneToMany(mappedBy = "endStation", fetch = FetchType.LAZY)
    private List<ArcEntity> endStationArcs = new ArrayList<>();

    @OneToMany(mappedBy = "startStation", fetch = FetchType.LAZY)
    private List<TicketEntity> startStationTickets = new ArrayList<>();

    @OneToMany(mappedBy = "finishStation", fetch = FetchType.LAZY)
    private List<TicketEntity> finishStationTickets = new ArrayList<>();


    public StationEntity() {
    }

    public StationEntity(String stationName) {
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<ArcEntity> getBeginStationArcs() {
        return beginStationArcs;
    }

    public void setBeginStationArcs(List<ArcEntity> beginStationArcs) {
        this.beginStationArcs = beginStationArcs;
    }

    public List<ArcEntity> getEndStationArcs() {
        return endStationArcs;
    }

    public void setEndStationArcs(List<ArcEntity> endStationArcs) {
        this.endStationArcs = endStationArcs;
    }

    public List<TicketEntity> getStartStationTickets() {
        return startStationTickets;
    }

    public void setStartStationTickets(List<TicketEntity> startStationTickets) {
        this.startStationTickets = startStationTickets;
    }

    public List<TicketEntity> getFinishStationTickets() {
        return finishStationTickets;
    }

    public void setFinishStationTickets(List<TicketEntity> finishStationTickets) {
        this.finishStationTickets = finishStationTickets;
    }
}
