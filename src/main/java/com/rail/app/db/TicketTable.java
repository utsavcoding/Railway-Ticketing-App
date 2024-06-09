package com.rail.app.db;

import com.rail.app.dto.Ticket;
import com.rail.app.exception.ResourceNotAvailableException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TicketTable {

    private static final Map<String, Ticket> table=new HashMap<>();

    public void write(Ticket ticket){
        table.put(ticket.getTicketId(),ticket);
    }

    public Ticket read(String ticketId) throws ResourceNotAvailableException {
        Ticket ticket=table.get(ticketId);
        if(ticket==null) throw new ResourceNotAvailableException("No ticket with this ticketID:"+ticketId);
        return table.get(ticketId);
    }

    public void delete(String ticketId) throws ResourceNotAvailableException {
        Ticket ticket=table.get(ticketId);
        if(ticket==null) throw new ResourceNotAvailableException("No Ticket with this ticketID:"+ticketId);
        table.remove(ticketId);
    }

    public void update(Ticket ticket){
        table.put(ticket.getTicketId(), ticket);
    }

    public List<Ticket> getAllTickets(String sectionName){
        List<Ticket> tickets=new ArrayList<>();
        for(String key:table.keySet()){
            Ticket ticket=table.get(key);
            if(sectionName==null || ticket.getSeat().getSeatId().contains(sectionName))
                tickets.add(ticket);
        }
        return tickets;
    }
}
