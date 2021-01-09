package com.evgen.rest;

import com.evgen.dto.station.*;
import com.evgen.service.StationService;
import com.evgen.service.exception.UseReservedTrainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StationRestController {

    private StationService stationService;

    public StationRestController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations")
    public List<StationDTO> getAllStations(){

        return stationService.getAllStations();
    }

    @GetMapping("/arcs")
    public List<ArcDTO> getAllArcs(){

        return stationService.getAllArcs();
    }

    @GetMapping("/routes")
    public List<RouteDTO> getAllRoutes(){

        return stationService.getAllRoutes();
    }

    // GET /routes/common?startStationId=1&finishStationId=2&dateFrom=2021-01-08&dateTo=2021-01-09
    @GetMapping("/routes/common")
    public List<Integer> getCommonRoutes(@RequestParam("startStationId") Integer startStationId,
                                         @RequestParam("finishStationId") Integer finishStationId,
                                         @RequestParam("dateFrom") String strDateFrom,
                                         @RequestParam("dateTo") String strDateTo){

        List<Integer> result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = sdf.parse(strDateFrom);
            Date dateTo = sdf.parse(strDateTo);

            result = stationService.getCommonRoutes(startStationId, finishStationId, dateFrom, dateTo);

        }catch (ParseException e){
            System.out.println(e);
            List<Integer> temp = new ArrayList<>();
            temp.add(-1);
            result = temp;
        }
        return result;
    }

    @GetMapping("/routes-ext")
    public List<RouteExtDTO> getAllRoutesExt(){

        return stationService.getAllRoutesExt();
    }

    @GetMapping("/station/{id}")
    public StationDTO getStation(@PathVariable("id") Integer id){

        return stationService.getStation(id);
    }

    // GET /station?stationName=Москва
    @GetMapping(value = "/station", params = "stationName")
    public StationDTO getStationByName(@RequestParam("stationName") String stationName){

        return stationService.getByName(stationName);
    }

    @GetMapping("/station/{id}/arrivals")
    public List<RoutePathDTO> getArrivals(@PathVariable("id") Integer id){

        return stationService.getArrivals(id);
    }

    @GetMapping("/station/{id}/departures")
    public List<RoutePathDTO> getDepartures(@PathVariable("id") Integer id){

        return stationService.getDepartures(id);
    }

    @GetMapping("/station/{id}/out-arcs")
    public List<ArcDTO> getOutArcs(@PathVariable("id") Integer id){

        return stationService.getOutArcs(id);
    }

    @GetMapping("/arc/{id}")
    public ArcDTO getArc(@PathVariable("id") Integer id){

        return stationService.getArc(id);
    }

    // GET /arc?beginStationId=1&endStationId=2
    @GetMapping(value = "/arc", params = {"beginStationId", "endStationId"})
    public ArcDTO getArcByStations(@RequestParam("beginStationId") Integer beginStationId,
                                   @RequestParam("endStationId") Integer endStationId){

        return stationService.getArcByStations(beginStationId, endStationId);
    }

    @GetMapping("/route/{id}")
    public RouteDTO getRoute(@PathVariable("id") Integer id){

        return stationService.getRoute(id);
    }

    // GET /route/{id}/length?startTime=2021-01-08T16:57:48&finishTime=2021-01-09T16:57:48
    @GetMapping("/route/{id}/length")
    public Integer getRouteLength(@PathVariable("id") Integer id,
                                  @RequestParam("startTime") String strStartTime,
                                  @RequestParam("finishTime") String strFinishTime){

        LocalDateTime startTime = LocalDateTime.parse(strStartTime);
        LocalDateTime finishTime = LocalDateTime.parse(strFinishTime);
        return stationService.getRouteLength(id, startTime, finishTime);
    }

    @GetMapping("/route/{id}/start-station/{startStationId}")
    public LocalDateTime getRouteStartTime(@PathVariable("id") Integer routeId,
                                           @PathVariable("startStationId") Integer startStationId){

        return stationService.getRouteStartTime(routeId, startStationId);
    }

    @GetMapping("/route/{id}/finish-station/{finishStationId}")
    public LocalDateTime getRouteFinishTime(@PathVariable("id") Integer routeId,
                                           @PathVariable("finishStationId") Integer finishStationId){

        return stationService.getRouteFinishTime(routeId, finishStationId);
    }

    @GetMapping("/route-ext/{id}")
    public RouteExtDTO getRouteExt(@PathVariable("id") Integer id){

        return stationService.getRouteExt(id);
    }

    @PostMapping("/station")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createStation(@RequestBody StationDTO station){

        return stationService.addStation(station);
    }

    @PostMapping("/arc")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createArc(@RequestBody ArcDTO arc){

        return stationService.addArc(arc);
    }

    @PostMapping("/route")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createRoute(@RequestBody CreateRouteDTO createRouteDTO) {

        int routeId = 0;
        try {
            routeId = stationService.createRoute(createRouteDTO.getRouteDTO(), createRouteDTO.getRoutePathDTOS());

        } catch (UseReservedTrainException e) {
            routeId = -1;
        }

        return routeId;
    }
}
