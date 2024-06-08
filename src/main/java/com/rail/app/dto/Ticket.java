package com.rail.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String ticketId;
    private User user;
    private Seat seat;
    private Station from;
    private Station to;
}
