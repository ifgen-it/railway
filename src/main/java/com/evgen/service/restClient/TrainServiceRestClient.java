package com.evgen.service.restClient;

import com.evgen.dto.train.TrainDTO;
import com.evgen.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TrainServiceRestClient implements TrainService{

    private static final String baseUrl = "http://localhost:8080/api/v1";

    @Override
    public List<TrainDTO> getAllTrains() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/trains";

        ResponseEntity<TrainDTO[]> response = null;
        try{
            response = template.getForEntity(url, TrainDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<TrainDTO> trains = Arrays.asList(response.getBody());
            return trains;
        }

        return null;
    }

    @Override
    public int addTrain(TrainDTO train) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/train";

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, train, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int trainId = response.getBody();
            return trainId;
        }

        return -1;
    }

    @Override
    public TrainDTO getTrain(int trainId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/train/" + trainId;

        ResponseEntity<TrainDTO> response = null;

        try {
            response = template.getForEntity(url, TrainDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            TrainDTO train = response.getBody();
            return train;
        }

        return null;
    }

    @Override
    public TrainDTO getByName(String trainName) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/train?trainName=" + trainName;

        ResponseEntity<TrainDTO> response = null;

        try {
            response = template.getForEntity(url, TrainDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            TrainDTO train = response.getBody();
            return train;
        }

        return null;
    }

    @Override
    public List<Integer> getFreeTrains(LocalDateTime startTime, LocalDateTime finishTime) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/trains/free?startTime="+ startTime +"&finishTime="+ finishTime;

        ResponseEntity<Integer[]> response = null;
        try{
            response = template.getForEntity(url, Integer[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<Integer> trainsId = Arrays.asList(response.getBody());
            return trainsId;
        }

        return null;
    }
}
