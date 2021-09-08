package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com/delphi/nice/training/service")
@ConfigurationProperties()
@RequestMapping(path = "/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final ExitService exitService;

    @Autowired
    public TicketController(TicketService ticketService, ExitService exitService) {
        this.ticketService = ticketService;
        this.exitService = exitService;
    }

    @PostMapping
    public String registerNewTicket() {
        return this.ticketService.generateTicket() ?
                "Ticket generated with id " + ticketService.getTicketID() + " successful!\nYour parking slot is " + ticketService.getParkingSlot() :
                "Something goes wrong!";
    }

    @GetMapping
    public List<TicketDto> getTickets() {
        return this.ticketService.getAllTickets();
    }

    @DeleteMapping(
            path = {"{uuid}"})
    public String deleteTicket(@PathVariable("uuid") Long id) {
        return this.exitService.exit(id) ? this.exitService.getPayMessage() : "Something goes wrong";
    }
}
