package com.evgen.dto.station;

import com.evgen.entity.station.ArcEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArcDTO implements Serializable {

    private int arcId;

    private StationDTO beginStation;

    private StationDTO endStation;

    private int length;

    private List<Integer> routePaths;

    public static ArcDTO entityToDTO(ArcEntity entity) {
        ArcDTO dto = new ArcDTO(entity.getArcId(),
                StationDTO.entityToDTO(entity.getBeginStation()),
                StationDTO.entityToDTO(entity.getEndStation()),
                entity.getLength());

        List<Integer> routePaths = new ArrayList<>();
        entity.getRoutePaths().forEach(r -> routePaths.add(r.getRoutePathId()));
        dto.setRoutePaths(routePaths);
        return dto;
    }

    public static ArcEntity dtoToEntity(ArcDTO dto) {
        return new ArcEntity(dto.getArcId(),
                StationDTO.dtoToEntity(dto.getBeginStation()),
                StationDTO.dtoToEntity(dto.getEndStation()),
                dto.getLength());
    }

    public ArcDTO() {
    }

    public ArcDTO(int arcId, StationDTO beginStation, StationDTO endStation, int length) {
        this.arcId = arcId;
        this.beginStation = beginStation;
        this.endStation = endStation;
        this.length = length;
    }

    public int getArcId() {
        return arcId;
    }

    public void setArcId(int arcId) {
        this.arcId = arcId;
    }

    public StationDTO getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(StationDTO beginStation) {
        this.beginStation = beginStation;
    }

    public StationDTO getEndStation() {
        return endStation;
    }

    public void setEndStation(StationDTO endStation) {
        this.endStation = endStation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Integer> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<Integer> routePaths) {
        this.routePaths = routePaths;
    }

    @Override
    public String toString() {
        return "ArcDTO{" +
                "arcId=" + arcId +
                ", beginStation=" + beginStation.getStationName() +
                ", endStation=" + endStation.getStationName() +
                ", length=" + length +
                '}';
    }
}
