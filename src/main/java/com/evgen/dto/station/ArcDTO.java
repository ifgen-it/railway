package com.evgen.dto.station;

import java.io.Serializable;
import java.util.List;

public class ArcDTO implements Serializable {

    private int arcId;

    private StationDTO beginStation;

    private StationDTO endStation;

    private int length;

    private List<RoutePathDTO> routePaths;

    public ArcDTO() {
    }

    public ArcDTO(StationDTO beginStation, StationDTO endStation, int length) {

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

    public List<RoutePathDTO> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<RoutePathDTO> routePaths) {
        this.routePaths = routePaths;
    }

    @Override
    public String toString() {
        return "ArcDTO{" +
                "arcId=" + arcId +
                ", beginStation=" + beginStation +
                ", endStation=" + endStation +
                ", length=" + length +
                '}';
    }
}
