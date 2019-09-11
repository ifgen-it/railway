package com.evgen.service;

import com.evgen.dao.RoleDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dao.impl.RoleDAOImpl;
import com.evgen.dao.impl.UserDAOImpl;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    @Mock
    private UserEntity userEntity;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getbyEmailTest(){

        String email = "adminEmail";
        when(userDAO.getByEmail(email)).thenReturn(new UserEntity());
        UserDTO userDTO = userService.getByEmail(email);
        assertNotNull(userDTO);
    }

    @Test
    public void getbyEmailNullTest(){

        String email = "";
        when(userDAO.getByEmail(email)).thenReturn(null);
        UserDTO userDTO = userService.getByEmail(email);
        assertNull(userDTO);
    }

    @Test
    public void getAllUsersTest(){

        when(userDAO.getAll()).thenReturn(new ArrayList<UserEntity>());
        List<UserDTO> userDTOS = userService.getAllUsers();
        assertNotNull(userDTOS);
    }

    @Test
    public void getUserTest(){
        int userId = 1;
        when(userDAO.get(userId)).thenReturn(new UserEntity());
        UserDTO userDTO = userService.getUser(userId);
        assertNotNull(userDTO);
    }

    @Test
    public void getTicketsTest(){
        int userId = 2;
        when(userDAO.get(userId)).thenReturn(userEntity);
        when(userEntity.getTickets()).thenReturn(new ArrayList<TicketEntity>());

        List<TicketDTO> ticketDTOS = userService.getTickets(userId);

    }


}
