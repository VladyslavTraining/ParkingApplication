package com.delphi.nice.training.ticket;

import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.reader.TicketReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoService implements TicketDao {
    private final String ticketPath;

    public TicketDaoService(@Value("${path.ticket}") String ticketPath) {
        this.ticketPath = ticketPath;
    }

    @Override
    public Ticket selectTicketByUuid(long uuid) {
        return getAllTickets().stream()
                .filter(ticket -> ticket.getUuid() == uuid)
                .findFirst().orElseThrow(() -> new UserNotFoundException(String.format("ticket with id %d ",uuid)));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new TicketReader().getJsonArr(ticketPath);
    }

}
