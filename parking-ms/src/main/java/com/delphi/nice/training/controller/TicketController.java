package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.Valet;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {
    private final Valet valet;

    @PostMapping("api/ticket")
    @PreAuthorize("hasAuthority('user:write')")
    public TicketDto registerNewTicket() {
        return valet.parkTheCar();
    }

    @GetMapping("api/ticket/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<JSONObject> getAllTickets() {
        return valet.getAllTickets();
    }

    @GetMapping("api/ticket/{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public TicketDto getTicket(@PathVariable long uuid) {
        return valet.getTicketById(uuid);
    }

    @DeleteMapping("api/ticket/{uuid}")
    @PreAuthorize("hasAuthority('user:write')")
    public String deleteTicket(@PathVariable long uuid) {
        return valet.exitTheCar(uuid);
    }
}
