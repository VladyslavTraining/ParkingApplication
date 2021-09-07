package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com/delphi/nice/training/service")
@PropertySource("classpath:/application.properties")
@RequestMapping(path = "/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public void registerNewTicket() {
        ticketService.generateTicket();
    }

    @GetMapping
    public List<TicketDto> getTickets() {
        return ticketService.getAllTickets();
    }
}
