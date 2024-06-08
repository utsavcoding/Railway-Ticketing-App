package com.rail.app.controller;

import com.rail.app.db.TicketTable;
import com.rail.app.dto.Receipt;
import com.rail.app.dto.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/rail")
public class TicketingController {

    @PostMapping("/submit/receipt")
    public ResponseEntity<String> submitReceiptToBookTicket(
            @RequestBody Receipt receipt
            ){
        System.out.println(receipt.toString());
        return ResponseEntity.created(URI.create("/rail/ticket/random-1")).build();
    }

    @GetMapping("/tickets")
    public ResponseEntity<String> getAllTicketBooking(){
        return ResponseEntity.ok("Ticket Bookings are as follows:"+
                "\nXXXX\n" +
                "XXOX\n" +
                "XXOO");
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

    @PutMapping("/ticket/edit")
    public ResponseEntity<Void> updateTicketById(
            @RequestBody Ticket ticket
            ){
        System.out.println(ticket.toString());
        TicketTable ticketTable=new TicketTable();
        return ResponseEntity.created(URI.create("/rail/ticket/"+ticket.getTicketId())).build();
    }
}
