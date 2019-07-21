package com.evgen;

import com.evgen.model.Passenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name,
            Model model){
        model.addAttribute("title",
                "Passenger railway traffic information system");
        model.addAttribute("msg", "Hello, " + name + "! Wanna buy ticket?");
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw(){
        String str = "title:Passenger railway traffic information system";
        return str;
    }

    @GetMapping("/passengers")
    public String getPassengers(Model model){

        List<Passenger> passengers = Arrays.asList(
                new Passenger("evgen", "smirnov", "evg@mail.ru"),
                new Passenger("Stas", "Alekhnovich", "slavyan@msk.ru"),
                new Passenger("Kate", "Neratova", "kate@usa.com")
        );

        model.addAttribute("passengers", passengers);

        return "passengers";
    }
}
