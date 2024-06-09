package com.rail.app.controller;

import com.rail.app.SampleBaseTest;
import com.rail.app.dto.Receipt;
import com.rail.app.dto.Seat;
import com.rail.app.dto.Station;
import com.rail.app.dto.Ticket;
import com.rail.app.service.TicketingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TicketingController.class)
public class TicketingControllerTest extends SampleBaseTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketingService ticketingService;


    @Test
    public void submitReceiptToBookTicketSuccess() throws Exception {
        Mockito.when(ticketingService.bookTicket(Mockito.any(Receipt.class))).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/rail/submit/receipt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(receiptPayload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void getAllTicketBookingSuccess() throws Exception {
        Ticket ticket1=new Ticket(
                "TKT123",
                user1,
                new Seat("SA-1A"),
                new Station(receipt.getFrom()),
                new Station(receipt.getTo())
        );
        Ticket ticket2=new Ticket(
                "TKT124",
                user2,
                new Seat("SB-1B"),
                new Station(receipt.getFrom()),
                new Station(receipt.getTo())
        );
        List<Ticket> mockList=new ArrayList<>();
        mockList.add(ticket1);
        mockList.add(ticket2);
        Mockito.when(ticketingService.getAllTickets(Mockito.any(String.class))).thenReturn(mockList);

        mockMvc.perform(MockMvcRequestBuilders.get("/rail/tickets?sectionName=SA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticketId").value("TKT123"));
    }

    @Test
    public void getTicketByIdSuccess() throws Exception {
        Mockito.when(ticketingService.getTicket(Mockito.anyString())).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.get("/rail/ticket/TKT123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketId").value(ticket.getTicketId()));
    }

    @Test
    public void deleteTicketByIdSuccess() throws Exception {
        Mockito.doNothing().when(ticketingService).deleteTicket(Mockito.anyString());

        mockMvc.perform(MockMvcRequestBuilders.delete("/rail/ticket/TKT123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Ticket booking is cancelled"));
    }

    @Test
    public void updateTicketSuccess() throws Exception {
        Mockito.doNothing().when(ticketingService).updateTicket(Mockito.any(Ticket.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/rail/ticket/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketPayload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Ticket is updated"));
    }
}
