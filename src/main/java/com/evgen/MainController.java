package com.evgen;

import com.evgen.dao.UserDAO;
import com.evgen.model.User;
import com.evgen.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public String getUsers(Model model) {

        model.addAttribute("users", userDAO.getAll());

        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(){
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String SignUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("birthday") String strBirthday,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {

        System.out.println("---> in the SignUp");

        User user = new User();

        String firstNameError = "";
        String lastNameError = "";
        String birthdayError = "";
        String emailError = "";
        String passwordError = "";
        boolean error = false;

        try {
            Date birthday = new SimpleDateFormat("dd.MM.yyyy").parse(strBirthday);
            user.setBirthday(birthday);
        } catch (ParseException e) {
            System.out.println("---> Birthday error");
            error = true;
            birthdayError = "Required format: DD.MM.YYYY";
        }

        if (email.equals("")){
            emailError = "Email is required";
            error = true;
        }
        if (firstName.equals("")){
            firstNameError = "First name is required";
            error = true;
        }
        if (lastName.equals("")){
            lastNameError = "Last name is required";
            error = true;
        }
        if (password.equals("")){
            passwordError = "Password is required";
            error = true;
        }

        if (error == true){
            model.addAttribute("firstNameError", firstNameError);
            model.addAttribute("lastNameError", lastNameError);
            model.addAttribute("birthdayError", birthdayError);
            model.addAttribute("emailError", emailError);
            model.addAttribute("passwordError", passwordError);

            return "/sign_up";
        }

        // CHECK THE SAME EMAIL EXISTENCE IN DB
        boolean emailExist =  userValidator.checkEmailExistence(user);

        if (emailExist){
            model.addAttribute("emailError", "This email already in use");
            return "/sign_up";
        }

        // FORM WAS VALIDATED
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        userDAO.add(user);

        return "redirect:/users";
    }
}
