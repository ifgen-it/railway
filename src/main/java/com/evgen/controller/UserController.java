package com.evgen.controller;

import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.SecurityService;
import com.evgen.service.UserService;
import com.evgen.util.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private static final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    private UserValidator userValidator;

    private SecurityService securityService;

    public UserController(@Qualifier("userServiceRestClient") UserService userService,
                          UserValidator userValidator,
                          SecurityService securityService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityService = securityService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "/users";
    }

    @PostMapping("/users")
    public String changeUsers(@RequestParam(value = "delete-user", required = false) String[] delUsers,
                              @RequestParam(value = "make-admin", required = false) String[] makeAdmin,
                              @RequestParam(value = "make-user", required = false) String[] makeUser,
                              Model model) {

        logger.info("del Users: " + delUsers);
        logger.info("make Admins: " + makeAdmin);
        logger.info("make Users: " + makeUser);

        if ((delUsers == null) && (makeAdmin == null) && (makeUser == null)) {
            return "redirect:/users";
        }

        boolean deleteMyself = false;
        int mySelfUserId = securityService.getAuthUser().getUserId();

        // MAKE ADMINS
        if (makeAdmin != null) {
            for (int i = 0; i < makeAdmin.length; i++) {
                int makeAdminUserId = Integer.parseInt(makeAdmin[i]);
                logger.info("wanna make ADMIN user with id: " + makeAdminUserId);

                userService.changeRole(makeAdminUserId, "ROLE_ADMIN");
            }
        }

        // MAKE USERS
        if (makeUser != null) {
            for (int i = 0; i < makeUser.length; i++) {
                int makeUserUserId = Integer.parseInt(makeUser[i]);
                logger.info("wanna make USER user with id: " + makeUserUserId);

                if (makeUserUserId == mySelfUserId) {
                    continue;
                }
                userService.changeRole(makeUserUserId, "ROLE_USER");
            }
        }

        // DELETE USERS
        if (delUsers != null) {
            for (int i = 0; i < delUsers.length; i++) {
                int userDelId = Integer.parseInt(delUsers[i]);
                logger.info("wanna delete user with id: " + userDelId);

                if (userDelId == mySelfUserId) {
                    deleteMyself = true;
                }
                userService.removeWith(userDelId);
            }
        }
        if (deleteMyself == true) {
            return "redirect:/logout";
        }

        return "redirect:/users";
    }

    @GetMapping("/sign-up")
    public String getSignUp() {

        return "/sign_up";
    }

    @PostMapping("/sign-up")
    public String SignUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("birthday") String strBirthday,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {

        logger.info("Date = " + strBirthday);
        UserDTO user = new UserDTO();

        RoleDTO roleDTO = userService.getRoleByName("ROLE_USER");
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
            logger.warn("Birthday error");
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
            logger.warn("Validating error");

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
        logger.info("user registered, user = " + user);

        return "redirect:/";
    }


    @GetMapping("/accounts")
    public String getAccounts(@RequestParam(name = "id", required = false) String strUserId,
                              Model model) {


        // NEED TO VALIDATE STR USER ID
        int userId = 0;

        if (strUserId == null) {

            List<UserDTO> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "/accounts";
        } else {

            // GIVE SELECTED ACCOUNT TO ADMIN
            try {
                userId = Integer.parseInt(strUserId);
            } catch (Exception e) {
                logger.warn(e.getMessage());
                logger.warn("Parse error user id");
                return "redirect:/accounts";
            }

            if (userId <= 0) {
                logger.warn("User id <= 0");
                return "redirect:/accounts";
            }

            if (userService.getUser(userId) == null) {
                logger.warn("No such user");
                return "redirect:/accounts";
            }

            // ALL RIGHT - FETCH USER DETAILS

            UserDTO user = userService.getUser(userId);
            List<TicketDTO> tickets = userService.getTickets(userId);

            model.addAttribute("user", user);
            model.addAttribute("tickets", tickets);

            //ALSO SOURCE INFO
            List<UserDTO> users = userService.getAllUsers();
            model.addAttribute("users", users);

            return "/accounts";


        }


    }

    @GetMapping("/account")
    public String getAccount(Model model) {

        UserDTO user = securityService.getAuthUser();
        logger.info("user: " + user);

        if (user == null) {
            return "redirect:/";
        }
        List<TicketDTO> tickets = userService.getTickets(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);

        return "/account";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {

        logger.info("In the Login Request Mapping, error = " + error);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("passwordError", "User not found or bad credentials");
        }

        return "/sign_in";
    }
}
