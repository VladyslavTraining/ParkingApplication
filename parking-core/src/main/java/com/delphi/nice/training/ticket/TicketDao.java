package com.delphi.nice.training.ticket;

import java.util.List;

public interface TicketDao {
    Ticket selectTicketByUuid(long uuid);

    List<Ticket> getAllTickets();

    List<Ticket> getAllValidTickets();
}
