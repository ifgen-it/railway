package com.evgen.rest;

import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private UserService userService;

    public UserRestController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users") //++
    public List<UserDTO> getAllUsers(){

        List<UserDTO> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/user/{id}") //++
    public UserDTO getUser(@PathVariable("id") Integer id){

        return userService.getUser(id);
    }

    // GET /user?email=evgen@evgen
    @GetMapping(value = "/user", params = "email") //++
    public UserDTO getUserByEmail(@RequestParam("email") String email){

        return userService.getByEmail(email);
    }

    // Есть зависимость от таблицы Ticket
    @GetMapping("/user/{id}/tickets") //++
    public List<TicketDTO> getUserTickets(@PathVariable("id") Integer id){

        return userService.getTickets(id);
    }

    @DeleteMapping("/user/{id}") //++
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer id){

        userService.removeWith(id);
    }

    @GetMapping("/role/{roleName}") //++
    public RoleDTO getRoleByName(@PathVariable("roleName") String roleName){

        return userService.getRoleByName(roleName);
    }

    @PostMapping("/user") //++
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createUser(@RequestBody UserDTO user){

        return userService.addUser(user);
    }

    // PUT /user/{id}/role?newRole=ROLE_ADMIN
    @PutMapping(value = "/user/{id}/role", params = "newRole") //++
    public void changeUserRole(@PathVariable("id") Integer id,
                               @RequestParam("newRole") String newRole){

        userService.changeRole(id, newRole);
    }
}
