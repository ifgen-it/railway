package com.evgen.controller;

import com.evgen.service.MessageService;
import com.evgen.service.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import java.util.List;

@Controller
public class MessageController {  // FOR TESTING - SEND MESSAGE TO JMS

    private static final Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier("stationServiceImpl")
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

        logger.info("Message was sent to " + stationName);

        return "/send_message";
    }


}
