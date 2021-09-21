package com.delphi.nice.training.controller;

import com.delphi.nice.training.ticket.Ticket;
import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.service.ValetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final ValetService valetService;

    @PostMapping("api/ticket")
    @PreAuthorize("hasAuthority('user:write')")
    public Ticket registerNewTicket() {
        return valetService.parkTheCar();
    }

    @GetMapping("api/ticket/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Ticket> getAllTickets() {
        return valetService.getAllTickets();
    }

    @GetMapping("api/ticket/{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public Ticket getTicket(@PathVariable long uuid) {
        return valetService.getTicketById(uuid);
    }

    @DeleteMapping("api/ticket/{uuid}")
    @PreAuthorize("hasAuthority('user:write')")
    public String deleteTicket(@PathVariable long uuid) {
        return valetService.exitTheCar(uuid);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }
}
