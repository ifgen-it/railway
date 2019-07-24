package com.evgen;

import com.evgen.model.Passenger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    List<Passenger> passengers = new ArrayList<>();


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

        model.addAttribute("passengers", passengers);

        return "passengers";
    }

    @GetMapping("/passengers/new")
    public String getSignUp(){
        return "sign_up";
    }

    @PostMapping("/passengers/new")
    public String SignUp(@ModelAttribute Passenger passenger, Model model){
        String nameError = "";
        String surnameError = "";
        String emailError = "";
        boolean error = false;

        String name = passenger.getName().trim();
        String surname = passenger.getSurname().trim();
        String email = passenger.getEmail().trim();

        if (email.equals("")){
            emailError = "Email is required";
            error = true;
        }
        if (name.equals("")){
            nameError = "Name is required";
            error = true;
        }
        if (surname.equals("")){
            surnameError = "Surname is required";
            error = true;
        }

        if (error == true){
            model.addAttribute("nameError", nameError);
            model.addAttribute("surnameError", surnameError);
            model.addAttribute("emailError", emailError);

            return "/sign_up";
        }

        passengers.add(passenger);
        return "redirect:/passengers";
    }
}
