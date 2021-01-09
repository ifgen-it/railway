package com.evgen.dto.station;

import com.evgen.entity.station.StationEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StationDTO implements Serializable {

    private int stationId;

    private String stationName;

    private List<Integer> beginStationArcs;

    private List<Integer> endStationArcs;

    private List<Integer> startStationTickets;

    private List<Integer> finishStationTickets;

    public static StationDTO entityToDTO(StationEntity entity){
        StationDTO dto = new StationDTO(entity.getStationId(),
                entity.getStationName());

        List<Integer> beginStationArcs = new ArrayList<>();
        entity.getBeginStationArcs().forEach(b -> beginStationArcs.add(b.getArcId()));
        dto.setBeginStationArcs(beginStationArcs);

        List<Integer> endStationArcs = new ArrayList<>();
        entity.getEndStationArcs().forEach(e -> endStationArcs.add(e.getArcId()));
        dto.setEndStationArcs(endStationArcs);

        List<Integer> startStationTickets = new ArrayList<>();
        entity.getStartStationTickets().forEach(t -> startStationTickets.add(t.getTicketId()));
        dto.setStartStationTickets(startStationTickets);

        List<Integer> finishStationTickets = new ArrayList<>();
        entity.getFinishStationTickets().forEach(t -> finishStationTickets.add(t.getTicketId()));
        dto.setFinishStationTickets(finishStationTickets);

        return dto;
    }
    public static StationEntity dtoToEntity(StationDTO dto){
        return new StationEntity(dto.getStationId(),
                dto.getStationName());
    }

    public StationDTO(int stationId, String stationName) {
        this.stationId = stationId;
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

    public List<Integer> getBeginStationArcs() {
        return beginStationArcs;
    }

    public void setBeginStationArcs(List<Integer> beginStationArcs) {
        this.beginStationArcs = beginStationArcs;
    }

    public List<Integer> getEndStationArcs() {
        return endStationArcs;
    }

    public void setEndStationArcs(List<Integer> endStationArcs) {
        this.endStationArcs = endStationArcs;
    }

    public List<Integer> getStartStationTickets() {
        return startStationTickets;
    }

    public void setStartStationTickets(List<Integer> startStationTickets) {
        this.startStationTickets = startStationTickets;
    }

    public List<Integer> getFinishStationTickets() {
        return finishStationTickets;
    }

    public void setFinishStationTickets(List<Integer> finishStationTickets) {
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
