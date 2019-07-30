package com.evgen;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import com.evgen.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.UserDataHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name,
            Model model){
        model.addAttribute("title",
                "User railway traffic information system");
        model.addAttribute("msg", "Hello, " + name + "! Wanna buy ticket?");
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw(){
        String str = "title: Passenger railway traffic information system";
        return str;
    }

    @GetMapping("/users")
    public String getUsers(Model model) throws SQLException {

        model.addAttribute("users", userDAO.getAll());

        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(){
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String SignUp(@ModelAttribute User user, Model model) throws SQLException {
        String nameError = "";
        String surnameError = "";
        String emailError = "";
        boolean error = false;

        String name = user.getFirstName().trim();
        String surname = user.getLastName().trim();
        String email = user.getEmail().trim();

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

        // CHECK THE SAME EMAIL EXISTENCE IN DB
        emailError =  userValidator.checkEmailExistence(user);

        if (!emailError.equals("")){
            model.addAttribute("emailError", emailError);
            return "/sign_up";
        }
        userDAO.add(user);

        return "redirect:/users";
    }
}
