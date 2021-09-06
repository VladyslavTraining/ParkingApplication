package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
//@RequestMapping(path = "/api/v1/ticket")
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

    @GetMapping("/hello")
    public String getTickets() {
        return "ticketService.getAllTickets()";
    }
}
