package com.delphi.nice.training.service;

import com.delphi.nice.training.ticket.Ticket;

import java.util.List;

public interface Valet {
    Ticket parkTheCar();
    String exitTheCar(long uuid);
    Ticket getTicketById(long uuid);
    List<Ticket> getAllTickets();

}
