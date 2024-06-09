package com.rail.app.service;

import com.rail.app.SampleBaseTest;
import com.rail.app.db.SeatTable;
import com.rail.app.db.TicketTable;
import com.rail.app.dto.Seat;
import com.rail.app.dto.Station;
import com.rail.app.dto.Ticket;
import com.rail.app.exception.ResourceNotAvailableException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TicketingServiceTest extends SampleBaseTest {

    @Mock
    private TicketTable mockedTicketTable;
    @Mock
    private SeatTable mockedSeatTable;
    @InjectMocks
    private TicketingService ticketingService;

    @Test
    public void bookTicket() throws ResourceNotAvailableException {
        Ticket ticket=ticketingService.bookTicket(receipt);
        assertNotNull(ticket);
        assertEquals(user1.getEmail(),ticket.getUser().getEmail());
    }

    @Test
    public void getTicket() throws ResourceNotAvailableException {
        Mockito.when(mockedTicketTable.read(Mockito.anyString())).thenReturn(new Ticket());
        Ticket ticket=ticketingService.getTicket("TICKETID123");
        assertNotNull(ticket);
    }

    @Test
    public void deleteTicket() throws ResourceNotAvailableException {
        Mockito.doNothing().when(mockedTicketTable).delete(Mockito.anyString());
        ticketingService.deleteTicket("TICKETID123");
        Mockito.verify(mockedTicketTable).delete(Mockito.anyString());
    }

    @Test
    public void updateTicket() throws ResourceNotAvailableException {
        Mockito.doNothing().when(mockedTicketTable).update(Mockito.any(Ticket.class));
        Mockito.when(mockedSeatTable.getAvailable(Mockito.anyString())).thenReturn(seat);
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
                new Seat("SB-1B"),
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
