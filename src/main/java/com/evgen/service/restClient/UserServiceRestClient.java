package com.evgen.service.restClient;

import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.RoleDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserServiceRestClient implements UserService {

    private static final String baseUrl = "http://localhost:8080/api/v1";

    @Override
    public List<UserDTO> getAllUsers() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/users";

        ResponseEntity<UserDTO[]> response = null;
        try{
            response = template.getForEntity(url, UserDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<UserDTO> users = Arrays.asList(response.getBody());
            return users;
        }

        return null;
    }

    @Override
    public UserDTO getByEmail(String email) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user?email=" + email;

        ResponseEntity<UserDTO> response = null;

        try {
            response = template.getForEntity(url, UserDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            UserDTO user = response.getBody();
            return user;
        }

        return null;
    }

    @Override
    public int addUser(UserDTO user) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user";

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, user, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int userId = response.getBody();
            return userId;
        }

        return -1;
    }

    @Override
    public void removeWith(int id) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user/" + id;

        try {
            template.delete(url);
        }
        catch (RestClientException e){
            System.out.println(e);
        }
    }

    // not used
    @Override
    public List<RoleDTO> getAllRoles() {
        return null;
    }

    @Override
    public UserDTO getUser(int userId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user/" + userId;

        ResponseEntity<UserDTO> response = null;

        try {
            response = template.getForEntity(url, UserDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            UserDTO user = response.getBody();
            return user;
        }

        return null;
    }

    // not used
    @Override
    public RoleDTO getRole(int roleId) {
        return null;
    }

    @Override
    public RoleDTO getRoleByName(String roleName) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/role/" + roleName;

        ResponseEntity<RoleDTO> response = null;

        try {
            response = template.getForEntity(url, RoleDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            RoleDTO role = response.getBody();
            return role;
        }

        return null;
    }

    @Override
    public List<TicketDTO> getTickets(int userId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user/" + userId + "/tickets";

        ResponseEntity<TicketDTO[]> response = null;
        try{
            response = template.getForEntity(url, TicketDTO[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<TicketDTO> tickets = Arrays.asList(response.getBody());
            return tickets;
        }

        return null;
    }

    @Override
    public void changeRole(int userId, String roleName) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/user/" + userId + "/role?newRole=" + roleName;

        try {
            template.put(url, null);
        }
        catch (RestClientException e){
            System.out.println(e);
        }
    }
}
