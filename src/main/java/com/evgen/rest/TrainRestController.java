package com.evgen.rest;

import com.evgen.dto.train.TrainDTO;
import com.evgen.service.TrainService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrainRestController {


    private TrainService trainService;

    public TrainRestController(@Qualifier("trainServiceImpl") TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/trains")
    public List<TrainDTO> getAllTrains(){

        return trainService.getAllTrains();
    }

    @GetMapping("/train/{id}")
    public TrainDTO getTrain(@PathVariable("id") Integer id){

        return trainService.getTrain(id);
    }

    // GET /train?trainName=T1000
    @GetMapping(value = "/train", params = "trainName")
    public TrainDTO getTrainByName(@RequestParam("trainName") String trainName){

        return trainService.getByName(trainName);
    }

    @PostMapping("/train")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createTrain(@RequestBody TrainDTO train){

        return trainService.addTrain(train);
    }

    // GET /trains/free?startTime=2021-01-08T16:57:48&finishTime=2021-01-09T16:57:48
    @GetMapping("/trains/free")
    public List<Integer> getFreeTrains(@RequestParam("startTime") String strStartTime,
                                       @RequestParam("finishTime") String strFinishTime){

        LocalDateTime startTime = LocalDateTime.parse(strStartTime);
        LocalDateTime finishTime = LocalDateTime.parse(strFinishTime);

        return trainService.getFreeTrains(startTime, finishTime);
    }
}
