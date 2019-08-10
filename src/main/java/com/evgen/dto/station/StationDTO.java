package com.evgen.dto.station;

import com.evgen.dto.ticket.TicketDTO;
import java.io.Serializable;
import java.util.List;

public class StationDTO implements Serializable {

    private int stationId;

    private String stationName;

    private List<ArcDTO> beginStationArcs;

    private List<ArcDTO> endStationArcs;

    private List<TicketDTO> startStationTickets;

    private List<TicketDTO> finishStationTickets;

    public StationDTO(String stationName) {
        this.stationName = stationName;
    }

    public StationDTO() {
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

    public List<ArcDTO> getBeginStationArcs() {
        return beginStationArcs;
    }

    public void setBeginStationArcs(List<ArcDTO> beginStationArcs) {
        this.beginStationArcs = beginStationArcs;
    }

    public List<ArcDTO> getEndStationArcs() {
        return endStationArcs;
    }

    public void setEndStationArcs(List<ArcDTO> endStationArcs) {
        this.endStationArcs = endStationArcs;
    }

    public List<TicketDTO> getStartStationTickets() {
        return startStationTickets;
    }

    public void setStartStationTickets(List<TicketDTO> startStationTickets) {
        this.startStationTickets = startStationTickets;
    }

    public List<TicketDTO> getFinishStationTickets() {
        return finishStationTickets;
    }

    public void setFinishStationTickets(List<TicketDTO> finishStationTickets) {
        this.finishStationTickets = finishStationTickets;
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
