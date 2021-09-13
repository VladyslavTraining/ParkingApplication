package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.Valet;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {
    private final Valet valet;

    @PostMapping("admin/ticket")
    public TicketDto registerNewTicket() {
        return valet.parkTheCar();
    }

    @GetMapping("admin/ticket/all")
    public List<JSONObject> getAllTickets() {
        return valet.getAllTickets();
    }

    @GetMapping("api/ticket/{uuid}")
    public TicketDto getTicket(@PathVariable long uuid) {
        return valet.getTicketById(uuid);
    }

    @DeleteMapping("admin/ticket/{uuid}")
    public String deleteTicket(@PathVariable long uuid) {
        return valet.exitTheCar(uuid);
    }
}
