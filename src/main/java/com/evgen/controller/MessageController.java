package com.evgen.controller;

import com.evgen.service.MessageService;
import com.evgen.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private StationService stationService;

    @GetMapping("/send-message")
    public String getSendMessage(Model model) {
        System.out.println("----> Get Mapping send-message");
        model.addAttribute("stations", stationService.getAllStations());

        return "/send_message";
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam(name = "stationId") int stationId,
                            Model model) throws JMSException {
        System.out.println("----> Post Mapping send Message");
        model.addAttribute("stations", stationService.getAllStations());
        String stationName = stationService.getStation(stationId).getStationName();
        model.addAttribute("stationName", stationName);
        messageService.sendMessage(stationName);

        return "/send_message";
    }


}
