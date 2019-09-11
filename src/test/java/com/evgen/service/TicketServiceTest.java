package com.evgen.service;

import com.evgen.dao.TicketDAO;
import com.evgen.dao.UserDAO;
import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.station.StationDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.dto.user.UserDTO;
import com.evgen.entity.station.RouteEntity;
import com.evgen.entity.station.StationEntity;
import com.evgen.entity.ticket.TicketEntity;
import com.evgen.entity.user.UserEntity;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;
import com.evgen.service.impl.TicketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private UserDAO userDAO;



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getAllTicketsTest(){

        when(ticketDAO.getAll()).thenReturn(new ArrayList<TicketEntity>());
        List<TicketDTO> ticketDTOS = ticketService.getAllTickets();
        assertNotNull(ticketDTOS);
    }

    @Test
    public void getTicketTest(){
        int ticketId = 1;
        when(ticketDAO.get(ticketId)).thenReturn(new TicketEntity());
        TicketDTO ticketDTO = ticketService.get(ticketId);
        assertNotNull(ticketDTO);
    }

}
