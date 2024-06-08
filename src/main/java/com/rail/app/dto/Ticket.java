package com.rail.app.dto;

import lombok.Data;

@Data
public class Ticket {
    private String ticketId;
    private User user;
    private Seat seat;
    private Station from;
    private Station to;
}
