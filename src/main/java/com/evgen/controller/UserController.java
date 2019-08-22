package com.evgen.controller;

import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.UserService;
import com.evgen.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/users")
//    @Secured("ROLE_ADMIN")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "/users";
    }

    @PostMapping("/users")
//    @Secured("ROLE_ADMIN")
    public String removeUsers(@RequestParam(value = "delete-user", required = false) String[] delUsers,
                              Model model) {

        System.out.println("---> in the Users POST");
        System.out.println("del Users: " + delUsers);
        if (delUsers == null)
            return "redirect:/users";

        for (int i = 0; i < delUsers.length; i++) {
            int userDelId = Integer.parseInt(delUsers[i]);
            System.out.println("wanna delete user with id: " + userDelId);

            userService.removeWith(userDelId);
        }

        return "redirect:/users";
    }

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {

        model.addAttribute("roles", userService.getAllRoles());
        return "/sign_up";
    }

    @PostMapping("/sign-up")
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

        if (email.equals("")) {
            emailError = "Email is required";
            error = true;
        }
        if (firstName.equals("")) {
            firstNameError = "First name is required";
            error = true;
        }
        if (lastName.equals("")) {
            lastNameError = "Last name is required";
            error = true;
        }
        if (password.equals("")) {
            passwordError = "Password is required";
            error = true;
        }

        if (error == true) {
            model.addAttribute("firstNameError", firstNameError);
            model.addAttribute("lastNameError", lastNameError);
            model.addAttribute("birthdayError", birthdayError);
            model.addAttribute("emailError", emailError);
            model.addAttribute("passwordError", passwordError);

            return "/sign_up";
        }

        // CHECK THE SAME EMAIL EXISTENCE IN DB
        boolean emailExist = userValidator.checkEmailExistence(email);

        if (emailExist) {
            model.addAttribute("emailError", "This email already in use");
            return "/sign_up";
        }

        // FORM WAS VALIDATED
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        userService.addUser(user);

        return "redirect:/";
    }



    @GetMapping("/accounts")
//    @Secured("ROLE_ADMIN")
    public String getAccount(Model model) {

        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "/accounts";
    }

    @GetMapping("/account")
    public String getAccount(@RequestParam(name = "id", required = false) String strUserId,
                             Model model) {

        System.out.println("--------> In Get mapping Account id, id = " + strUserId);

        // NEED TO VALIDATE STR USER ID
        int userId = 0;

        if (strUserId == null) {
            System.out.println("strUserId = null");
            return "redirect:/";
        }

        try {
            userId = Integer.parseInt(strUserId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Parse error user id");
            return "redirect:/";
        }

        if (userId <= 0) {
            System.out.println("User id <= 0");
            return "redirect:/";
        }

        if (userService.getUser(userId) == null) {
            System.out.println("No such user");
            return "redirect:/";
        }

        // ALL RIGHT - FETCH USER DETAILS

        UserDTO user = userService.getUser(userId);
        List<TicketDTO> tickets = userService.getTickets(userId);

        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);

        return "/account";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {

        System.out.println("---> In the Login Request Mapping, error = " + error);

        if (Boolean.TRUE.equals(error)){
            model.addAttribute("passwordError", "User not found or bad credentials");
        }

        return "/sign_in";
    }
}
