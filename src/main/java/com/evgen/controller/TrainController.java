package com.evgen.controller;

import com.evgen.dto.train.TrainDTO;
import com.evgen.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrainController {

    @Autowired
    private TrainService trainService;


    @GetMapping("/trains")
    public String getTrains(Model model) {

        model.addAttribute("trains", trainService.getAllTrains());
        return "/trains";
    }

    @PostMapping("/trains")
    public String trains(@RequestParam(name = "trainName") String trainName,
            @RequestParam(name = "seatsAmount") int seatsAmount,
            Model model) {

        System.out.println("======= in the Post mapping Trains =======");

        System.out.println("--> trainName = " + trainName);
        System.out.println("--> seatsAmount = " + seatsAmount);

        // CHECKING FOR ERRORS
        String trainNameError = "";
        String seatsAmountError = "";
        boolean error = false;

        if (seatsAmount < 36 || seatsAmount > 540){
            seatsAmountError = "Seats amount must be in range: 36..540";
            error = true;
        }

        if (trainService.getByName(trainName) != null){
            trainNameError = "This train name already in use";
            error = true;
        }

        if (error == true){
            model.addAttribute("trainNameError", trainNameError);
            model.addAttribute("seatsAmountError", seatsAmountError);
            model.addAttribute("trains", trainService.getAllTrains());

            return "/trains";
        }

        // ADD TRAIN
        System.out.println("------- train is Ok, will ADD --------------");
        TrainDTO trainDTO = new TrainDTO();
        trainDTO.setTrainName(trainName);
        trainDTO.setSeatsAmount(seatsAmount);
        trainService.addTrain(trainDTO);
        System.out.println("------- train must be added --------------");

        model.addAttribute("trains", trainService.getAllTrains());

        return "/trains";
    }

}
