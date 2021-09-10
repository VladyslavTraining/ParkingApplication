package com.delphi.nice.training.controller;

import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final ExitService exitService;

    @PostMapping
    @ResponseBody
    public String registerNewTicket() {
        return this.ticketService.generateTicket() ?
                "Ticket generated with id " + ticketService.getTicketID() + " successful!\nYour parking slot is " + ticketService.getParkingSlot() :
                "Something goes wrong!";
    }

    @GetMapping
    public List<JSONObject> getTickets() {
        return this.ticketService.getAllTickets();
    }

    //    getTicket();

    @DeleteMapping(
            path = {"{uuid}"})
    public String deleteTicket(@PathVariable("uuid") Long id) {
        return this.exitService.exit(id) ? this.exitService.getPayMessage() : "Something goes wrong";
    }
}
