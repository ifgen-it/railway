package com.evgen.entity.station;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arc")
public class ArcEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arc_id", nullable = false)
    private int arcId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "begin_station", nullable = false)
    private StationEntity beginStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_station", nullable = false)
    private StationEntity endStation;

    @Column(name = "length")
    private int length;

    @OneToMany(mappedBy = "arc", fetch = FetchType.LAZY)
    private List<RoutePathEntity> routePaths = new ArrayList<>();

    public ArcEntity() {
    }

    public ArcEntity(StationEntity beginStation, StationEntity endStation, int length) {
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

    public StationEntity getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(StationEntity beginStation) {
        this.beginStation = beginStation;
    }

    public StationEntity getEndStation() {
        return endStation;
    }

    public void setEndStation(StationEntity endStation) {
        this.endStation = endStation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<RoutePathEntity> getRoutePaths() {
        return routePaths;
    }

    public void setRoutePaths(List<RoutePathEntity> routePaths) {
        this.routePaths = routePaths;
    }
}
