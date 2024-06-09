package com.rail.app.controller;

import com.rail.app.dto.Receipt;
import com.rail.app.dto.Ticket;
import com.rail.app.exception.ResourceNotAvailableException;
import com.rail.app.service.TicketingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rail")
@Tag(name="Railway APIs",description = "List of APIS for railway ticketing system")
public class TicketingController {

    @Autowired
    private TicketingService ticketingService;

    @PostMapping("/submit/receipt")
    @Operation(summary = "Submit ticket receipt", description = "Create a new ticket for user receipt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created successfully"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<String> submitReceiptToBookTicket(
            @RequestBody Receipt receipt
            ) throws ResourceNotAvailableException {
        Ticket bookedTicket=ticketingService.bookTicket(receipt);
        return ResponseEntity.created(URI.create("/rail/ticket/"+bookedTicket.getTicketId())).build();
    }

    @GetMapping("/tickets")
    @Operation(summary = "Get all booked tickets based on section", description = "If section provided, show tickets with the section passed as query param, else show all booked tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return list of booked tickets"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<Ticket>> getAllTicketBooking(
            @RequestParam(name = "sectionName",required = false) String sectionName
    ){
        return ResponseEntity.ok(ticketingService.getAllTickets(sectionName));
    }

    @GetMapping("/ticket/{ticketId}")
    @Operation(summary = "Get booked ticket for user by ticketId", description = "Get booked ticket by ticketId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return booked ticket for given ticketId"),
            @ApiResponse(responseCode = "404", description = "Resource not found for ticket not available"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Ticket> getTicketById(
            @PathVariable String ticketId
    ) throws ResourceNotAvailableException {
        return ResponseEntity.ok(ticketingService.getTicket(ticketId));
    }

    @DeleteMapping("/ticket/{ticketId}")
    @Operation(summary = "Delete booked ticket for user by ticketId", description = "Get booked ticket by ticketId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket is cancelled/deleted with a success message"),
            @ApiResponse(responseCode = "404", description = "Resource not found for ticket not available"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<String> deleteTicketById(
            @PathVariable String ticketId
    ) throws ResourceNotAvailableException {
        ticketingService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket booking is cancelled");
    }

    @PutMapping("/ticket/edit")
    @Operation(summary = "Update booked ticket with a new modified ticket details", description = "Update booked ticket with a new modified ticket details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket is modified/updated with a success message"),
            @ApiResponse(responseCode = "409", description = "Conflict with already booked seat"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<String> updateTicketById(
            @RequestBody Ticket ticket
            ) throws ResourceNotAvailableException {
        ticketingService.updateTicket(ticket);
        return ResponseEntity.ok("Ticket is updated");
    }
}