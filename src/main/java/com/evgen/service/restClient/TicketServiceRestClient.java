package com.evgen.service.restClient;

import com.evgen.dao.exception.BusySeatPurchaseException;
import com.evgen.dto.station.RouteExtDTO;
import com.evgen.dto.ticket.TicketDTO;
import com.evgen.service.TicketService;
import com.evgen.service.exception.TimeLimitPurchaseException;
import com.evgen.service.exception.TwinUserPurchaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TicketServiceRestClient implements TicketService {

    private static final String baseUrl = "http://localhost:8080/api/v1";

    @Override
    public List<TicketDTO> getAllTickets() {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/tickets";

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

    // not used
    @Override
    public int addTicket(TicketDTO ticket) {
        return 0;
    }

    @Override
    public List<Integer> getBusySeats(int routeId, LocalDateTime startTime, LocalDateTime finishTime) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/tickets/route/"+ routeId +"/busy-seats?startTime="+ startTime +"&finishTime="+ finishTime;

        ResponseEntity<Integer[]> response = null;
        try{
            response = template.getForEntity(url, Integer[].class);
        } catch (RestClientException e){
            System.out.println(e);
        }

        if (response != null){
            List<Integer> seatsId = Arrays.asList(response.getBody());
            return seatsId;
        }

        return null;
    }

    @Override
    public int buyTicket(RouteExtDTO routeExtDTO, int seatNumber, int userId) throws
            TimeLimitPurchaseException,
            TwinUserPurchaseException,
            BusySeatPurchaseException {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/ticket?seatNumber="+ seatNumber +"&userId="+ userId;

        ResponseEntity<Integer> response = null;

        try {
            response = template.postForEntity(url, routeExtDTO, Integer.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            int ticketId = response.getBody();

            if (ticketId > 0)
                return ticketId;
            else if (ticketId == -1)
                throw new TimeLimitPurchaseException("Train will start less than 10 minutes " +
                        "OR Train was gone already. Ticket purchasing was stopped");
            else if (ticketId == -2)
                throw new TwinUserPurchaseException("User with the same first name, " +
                        "last name and birthday already purchased ticket on this route");
            else if (ticketId == -3)
                throw new BusySeatPurchaseException("This seat number was purchased recently. " +
                        "Select another one");
            else
                return 0;
        }

        return 0;
    }

    @Override
    public List<TicketDTO> getTickets(int routeId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/tickets/route/" + routeId;

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
    public TicketDTO get(int ticketId) {

        RestTemplate template = new RestTemplate();
        String url = baseUrl + "/ticket/" + ticketId;

        ResponseEntity<TicketDTO> response = null;

        try {
            response = template.getForEntity(url, TicketDTO.class);
        }
        catch (RestClientException e){
            System.out.println(e);
        }

        if (response !=null){
            TicketDTO ticket = response.getBody();
            return ticket;
        }

        return null;
    }
}
