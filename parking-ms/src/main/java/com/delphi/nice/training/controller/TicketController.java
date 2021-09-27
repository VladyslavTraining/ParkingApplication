package com.delphi.nice.training.controller;

import com.delphi.nice.training.exception.HaveNoParkingSlotException;
import com.delphi.nice.training.exception.TicketIsNotValidException;
import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.service.ValetService;
import com.delphi.nice.training.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ticket")
public class TicketController {

    private final ValetService valetService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Ticket registerNewTicket() {
        return valetService.parkTheCar();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Ticket> getAllTickets() {
        return valetService.getAllTickets();
    }

    @GetMapping("{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public Ticket getTicket(@PathVariable long uuid) {
        return valetService.getTicketById(uuid);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'USER')")
    public String deleteTicket(@PathVariable long uuid) {
        return valetService.exitTheCar(uuid);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ticketIsNotValidHandler(TicketIsNotValidException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String haveNoParkingSlotException(HaveNoParkingSlotException ex) {
        return ex.getMessage();
    }
}
