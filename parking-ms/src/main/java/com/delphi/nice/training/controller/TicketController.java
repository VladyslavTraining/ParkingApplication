package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.Valet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final Valet valet;
    @PostMapping
    public TicketDto registerNewTicket() {
        return valet.parkTheCar();
//        ?
//        "Ticket generated with id " + ticketService.getTicketID() + " successful!\nYour parking slot is " + ticketService.getParkingSlot() :
//        "Something goes wrong!";
    }

    @GetMapping("{uuid}")
    public TicketDto getTicket(@PathVariable long uuid) {
        return valet.getTicketById(uuid);
    }

    @DeleteMapping(
            path = {"{uuid}"})
    public String deleteTicket(@PathVariable long uuid) {
        return valet.exitTheCar(uuid);
    }
}
