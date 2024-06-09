package com.rail.app.service;

import com.rail.app.db.SeatTable;
import com.rail.app.db.TicketTable;
import com.rail.app.dto.*;
import com.rail.app.exception.ResourceNotAvailableException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TicketingServiceTest {

    private User user1;
    private User user2;
    private Receipt receipt;
    private Seat seat;
    private Ticket ticket;
    @Mock
    private TicketTable mockedTicketTable;
    @Mock
    private SeatTable mockedSeatTable;
    @InjectMocks
    private TicketingService ticketingService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setup(){
        autoCloseable=MockitoAnnotations.openMocks(this);
        user1 =new User("johndoe@gmail.com","John","Doe");
        user2 =new User("pirates@gmail.com","Jack","Sparrow");
        receipt=new Receipt("LDN","FRA", user1,20.0);
        seat=new Seat("SA-2F");
        ticket=new Ticket(Utils.randomULIDGenerator(),user1,seat,new Station("LDN"),new Station("FRA"));
    }

    @AfterEach
    public void shutDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void bookTicket() throws ResourceNotAvailableException {
        Ticket ticket=ticketingService.bookTicket(receipt);
        assertNotNull(ticket);
        assertEquals(user1.getEmail(),ticket.getUser().getEmail());
    }

    @Test
    public void getTicket(){
        Mockito.when(mockedTicketTable.read(Mockito.anyString())).thenReturn(new Ticket());
        Ticket ticket=ticketingService.getTicket("TICKETID123");
        assertNotNull(ticket);
    }

    @Test
    public void deleteTicket(){
        Mockito.doNothing().when(mockedTicketTable).delete(Mockito.anyString());
        ticketingService.deleteTicket("TICKETID123");
        Mockito.verify(mockedTicketTable).delete(Mockito.anyString());
    }

    @Test
    public void updateTicket() throws ResourceNotAvailableException {
        Mockito.doNothing().when(mockedTicketTable).update(Mockito.any(Ticket.class));
        Mockito.when(mockedSeatTable.isBooked(Mockito.anyString())).thenReturn(false);
        ticketingService.updateTicket(ticket);
        Mockito.verify(mockedTicketTable).update(Mockito.any(Ticket.class));
    }

    @Test
    public void getAllTickets(){

        Ticket ticket1=new Ticket(
                Utils.randomULIDGenerator(),
                user1,
                new Seat("SA-1A"),
                new Station(receipt.getFrom()),
                new Station(receipt.getTo())
        );
        Ticket ticket2=new Ticket(
                Utils.randomULIDGenerator(),
                user2,
                new Seat("SA-1B"),
                new Station(receipt.getFrom()),
                new Station(receipt.getTo())
        );
        List<Ticket> mockList=new ArrayList<>();
        mockList.add(ticket1);
        mockList.add(ticket2);

        Mockito.when(mockedTicketTable.getAllTickets(Mockito.anyString())).thenReturn(mockList);
        List<Ticket> list=ticketingService.getAllTickets("SA");
        assertNotNull(list);
        assertEquals(2,list.size());
    }
}
