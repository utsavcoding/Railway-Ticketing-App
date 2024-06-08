package com.rail.app.db;

import com.rail.app.dto.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketTable {

    private static final Map<String, Ticket> table=new HashMap<>();

    public void write(Ticket ticket){
        table.put(ticket.getTicketId(),ticket);
    }

    public Ticket read(String ticketId){
        return table.get(ticketId);
    }

    public void delete(String ticketId){
        table.remove(ticketId);
    }

    public void update(Ticket ticket){
        table.put(ticket.getTicketId(), ticket);
    }
}
