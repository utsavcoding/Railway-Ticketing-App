package com.rail.app.service;

import com.rail.app.db.SeatTable;
import com.rail.app.db.TicketTable;
import com.rail.app.dto.*;
import com.rail.app.exception.ResourceNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketingService {

    @Autowired
    private TicketTable ticketTable;

    @Autowired
    private SeatTable seatTable;

    public Ticket bookTicket(Receipt receipt) throws ResourceNotAvailableException {
        Seat availableSeat=seatTable.getFirstAvailableSeat();
        Ticket ticket=new Ticket(
                Utils.randomULIDGenerator(),
                receipt.getUser(),
                availableSeat,
                new Station(receipt.getFrom()),
                new Station(receipt.getTo())
                );
        ticketTable.write(ticket);
        return ticket;
    }

    public void updateTicket(Ticket ticket){
        ticketTable.update(ticket);
    }

    public void deleteTicket(String ticketId){
        ticketTable.delete(ticketId);
    }

    public Ticket getTicket(String ticketId){
        return ticketTable.read(ticketId);
    }

    public List<Ticket> getAllTickets(String sectionName){
        return ticketTable.getAllTickets(sectionName);
    }
}
