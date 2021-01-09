package com.evgen.service.restClient;

import com.evgen.dto.station.*;
import com.evgen.service.StationService;
import com.evgen.service.exception.UseReservedTrainException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StationServiceRestClient implements StationService {

    private static final String baseUrl = "http://localhost:8080/api/v1";

    @Override
    public List<StationDTO> getAllStations() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/stations";

        ResponseEntity<StationDTO[]> response = null;
        try{
            response = template.getForEntity(url, StationDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<StationDTO> stations = Arrays.asList(response.getBody());
            return stations;
        }

        return null;
    }

    @Override
    public List<ArcDTO> getAllArcs() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/arcs";

        ResponseEntity<ArcDTO[]> response = null;
        try{
            response = template.getForEntity(url, ArcDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<ArcDTO> arcs = Arrays.asList(response.getBody());
            return arcs;
        }

        return null;
    }

    @Override
    public List<RouteDTO> getAllRoutes() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/routes";

        ResponseEntity<RouteDTO[]> response = null;
        try{
            response = template.getForEntity(url, RouteDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<RouteDTO> routes = Arrays.asList(response.getBody());
            return routes;
        }

        return null;
    }

    @Override
    public StationDTO getStation(int stationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station/" + stationId;

        ResponseEntity<StationDTO> response = null;

        try {
            response = template.getForEntity(url, StationDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            StationDTO station = response.getBody();
            return station;
        }

        return null;
    }

    @Override
    public ArcDTO getArc(int arcId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/arc/" + arcId;

        ResponseEntity<ArcDTO> response = null;

        try {
            response = template.getForEntity(url, ArcDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            ArcDTO arc = response.getBody();
            return arc;
        }

        return null;
    }

    @Override
    public RouteDTO getRoute(int routeId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route/" + routeId;

        ResponseEntity<RouteDTO> response = null;

        try {
            response = template.getForEntity(url, RouteDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            RouteDTO route = response.getBody();
            return route;
        }

        return null;
    }

    @Override
    public int addStation(StationDTO station) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station";

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, station, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int stationId = response.getBody();
            return stationId;
        }

        return -1;
    }

    // not used
    @Override
    public int addRoute(RouteDTO route) {
        return 0;
    }

    @Override
    public int addArc(ArcDTO arc) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/arc";

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, arc, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int arcId = response.getBody();
            return arcId;
        }

        return -1;
    }

    // not used
    @Override
    public int addRoutePath(RoutePathDTO routePath) {
        return 0;
    }

    @Override
    public List<RoutePathDTO> getArrivals(int stationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station/"+ stationId +"/arrivals";

        ResponseEntity<RoutePathDTO[]> response = null;
        try{
            response = template.getForEntity(url, RoutePathDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<RoutePathDTO> routePaths = Arrays.asList(response.getBody());
            return routePaths;
        }

        return null;
    }

    @Override
    public List<RoutePathDTO> getDepartures(int stationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station/"+ stationId +"/departures";

        ResponseEntity<RoutePathDTO[]> response = null;
        try{
            response = template.getForEntity(url, RoutePathDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<RoutePathDTO> routePaths = Arrays.asList(response.getBody());
            return routePaths;
        }

        return null;
    }

    @Override
    public StationDTO getByName(String stationName) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station?stationName=" + stationName;

        ResponseEntity<StationDTO> response = null;

        try {
            response = template.getForEntity(url, StationDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            StationDTO station = response.getBody();
            return station;
        }

        return null;
    }

    @Override
    public ArcDTO getArcByStations(int beginStationId, int endStationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/arc?beginStationId="+ beginStationId +"&endStationId=" + endStationId;

        ResponseEntity<ArcDTO> response = null;

        try {
            response = template.getForEntity(url, ArcDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            ArcDTO arc = response.getBody();
            return arc;
        }

        return null;
    }

    // not used
    @Override
    public RoutePathDTO getFirstArc(int routeId) {
        return null;
    }

    // not used
    @Override
    public RoutePathDTO getLastArc(int routeId) {
        return null;
    }

    @Override
    public List<RouteExtDTO> getAllRoutesExt() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/routes-ext";

        ResponseEntity<RouteExtDTO[]> response = null;
        try{
            response = template.getForEntity(url, RouteExtDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<RouteExtDTO> routesExt = Arrays.asList(response.getBody());
            return routesExt;
        }

        return null;
    }

    @Override
    public List<Integer> getCommonRoutes(int startStationId, int finishStationId, Date dateFrom, Date dateTo) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDateFrom = formatter.format(dateFrom);
        String strDateTo = formatter.format(dateTo);

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/routes/common?startStationId="+ startStationId
                +"&finishStationId="+ finishStationId +"&dateFrom="+ strDateFrom +"&dateTo="+ strDateTo;

        ResponseEntity<Integer[]> response = null;
        try{
            response = template.getForEntity(url, Integer[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<Integer> routesId = Arrays.asList(response.getBody());
            return routesId;
        }

        return null;
    }

    @Override
    public RouteExtDTO getRouteExt(int routeId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route-ext/" + routeId;

        ResponseEntity<RouteExtDTO> response = null;

        try {
            response = template.getForEntity(url, RouteExtDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            RouteExtDTO routeExt = response.getBody();
            return routeExt;
        }

        return null;
    }

    @Override
    public LocalDateTime getRouteStartTime(int routeId, int startStationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route/"+ routeId +"/start-station/"+ startStationId;

        ResponseEntity<LocalDateTime> response = null;

        try {
            response = template.getForEntity(url, LocalDateTime.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            LocalDateTime dateTime = response.getBody();
            return dateTime;
        }

        return null;
    }

    @Override
    public LocalDateTime getRouteFinishTime(int routeId, int finishStationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route/"+ routeId +"/finish-station/"+ finishStationId;

        ResponseEntity<LocalDateTime> response = null;

        try {
            response = template.getForEntity(url, LocalDateTime.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            LocalDateTime dateTime = response.getBody();
            return dateTime;
        }

        return null;
    }

    @Override
    public Integer getRouteLength(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route/"+ routeId +"/length?startTime="+ startTime +"&finishTime="+ finishTime;

        ResponseEntity<Integer> response = null;

        try {
            response = template.getForEntity(url, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            Integer length = response.getBody();
            return length;
        }

        return 0;
    }

    @Override
    public List<ArcDTO> getOutArcs(int stationId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/station/"+ stationId +"/out-arcs";

        ResponseEntity<ArcDTO[]> response = null;
        try{
            response = template.getForEntity(url, ArcDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<ArcDTO> arcs = Arrays.asList(response.getBody());
            return arcs;
        }

        return null;
    }

    @Override
    public int createRoute(RouteDTO routeDTO, List<RoutePathDTO> routePathDTOS) throws UseReservedTrainException {

        CreateRouteDTO dto = new CreateRouteDTO(routeDTO, routePathDTOS);

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/route";

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, dto, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int routeId = response.getBody();

            if(routeId > 0)
                return routeId;
            else if (routeId == -1)
                throw new UseReservedTrainException("Train was used in another route recently");
            else
                return 0;
        }

        return 0;
    }

    // not used
    @Override
    public List<String> getStationNames(int routeId) {
        return null;
    }
}
