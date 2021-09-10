package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.TicketService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@ComponentScan("com/delphi/nice/training/service")
@ConfigurationProperties
@RequestMapping(path = "/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final ExitService exitService;

//    @Autowired
    public TicketController(TicketService ticketService, ExitService exitService) {
        this.ticketService = ticketService;
        this.exitService = exitService;
    }

    @PostMapping
    public void registerNewTicket() {
        ticketService.generateTicket();
    }

    @GetMapping
    public List<JSONObject> getTickets() {
        return ticketService.getAllTickets();
    }
    @DeleteMapping(path = "{uuid}")
    public List<String> deleteTicket(@PathVariable("uuid") Long id) {
        exitService.exit(id);
        return Collections.singletonList(exitService.getPayMessage());
    }
}
