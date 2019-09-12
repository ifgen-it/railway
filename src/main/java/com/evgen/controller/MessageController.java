package com.evgen.controller;

import com.evgen.service.MessageService;
import com.evgen.service.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;

@Controller
public class MessageController {

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private StationService stationService;

    @GetMapping("/send-message")
    public String getSendMessage(Model model) {
        model.addAttribute("stations", stationService.getAllStations());

        return "/send_message";
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam(name = "stationId") int stationId,
                            Model model) throws JMSException {

        model.addAttribute("stations", stationService.getAllStations());
        String stationName = stationService.getStation(stationId).getStationName();
        model.addAttribute("stationName", stationName);
        messageService.sendMessage(stationName);
        logger.info("Message sent");

        return "/send_message";
    }


}
