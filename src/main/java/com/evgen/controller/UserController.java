package com.evgen.controller;

import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.UserService;
import com.evgen.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/account")
    public String getAccount(Model model){

        return "/account";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "/users";
    }

    @PostMapping("/users")
    public String removeUsers(@RequestParam(value = "delete-user", required = false) String[] delUsers,
                              Model model) {

        System.out.println("---> in the Users POST");
        System.out.println("del Users: " + delUsers);
        if (delUsers == null)
            return "redirect:/users";

        for (int i = 0; i < delUsers.length; i++){
            int userDelId = Integer.parseInt(delUsers[i]);
            System.out.println("wanna delete user with id: " + userDelId );

            userService.removeWith(userDelId);
        }

        return "redirect:/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model){

        model.addAttribute("roles", userService.getAllRoles());
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String SignUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("birthday") String strBirthday,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("userRoleId") String userRoleId,
                         Model model) {

        System.out.println("---> in the SignUp");
        System.out.println("Date = " + strBirthday);
        UserDTO user = new UserDTO();


        RoleDTO roleDTO = userService.getRole(Integer.parseInt(userRoleId));
        user.setRole(roleDTO);

        String firstNameError = "";
        String lastNameError = "";
        String birthdayError = "";
        String emailError = "";
        String passwordError = "";
        boolean error = false;

        try {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(strBirthday);
            user.setBirthday(birthday);
        } catch (ParseException e) {
            System.out.println("---> Birthday error");
            error = true;
            birthdayError = "Date is required"; //"Required format: DD.MM.YYYY";
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
        boolean emailExist =  userValidator.checkEmailExistence(email);

        if (emailExist){
            model.addAttribute("emailError", "This email already in use");
            return "/sign_up";
        }

        // FORM WAS VALIDATED
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        userService.addUser(user);

        return "redirect:/users";
    }

}
