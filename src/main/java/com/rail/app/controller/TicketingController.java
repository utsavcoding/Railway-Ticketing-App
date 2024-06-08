package com.rail.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rail")
public class TicketingController {

    @PostMapping("/submit/receipt")
    public ResponseEntity<String> submitReceiptToBookTicket(
            @RequestBody String body
    ){
        return ResponseEntity.ok("Ticket booked!!");
    }

    @GetMapping("/tickets")
    public ResponseEntity<String> getAllTicketBooking(){
        return ResponseEntity.ok("Ticket Bookings are as follows:");
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<String> getTicketById(
            @PathVariable String ticketId
    ){
        return ResponseEntity.ok("Ticket Booking for"+ticketId);
    }

    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<String> deleteTicketById(
            @PathVariable String ticketId
    ){
        return ResponseEntity.ok("Ticket Deleted for"+ticketId);
    }

    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<String> updateTicketById(
            @PathVariable String ticketId
    ){
        return ResponseEntity.ok("Ticket updated for"+ticketId);
    }
}
