package com.delphi.nice.training.reader;

import com.delphi.nice.training.ticket.Ticket;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TicketReader implements Reader{
    @Override
    public List<Ticket> getJsonArr(String filepath) {
        List<Ticket> tickets = new LinkedList<>();
        Ticket ticket;
        for(JSONObject object : new JSONReader().getJsonArr(filepath))
        {
            ticket = new Ticket();
            ticket.setId((long) object.get("id"));
            ticket.setUuid((long) object.get("uuid"));
            ticket.setEntranceDateTime(LocalDateTime.parse(object.get("entranceTime").toString()));
            ticket.setValid((boolean) object.get("isValid"));
            tickets.add(ticket);
        }
        return tickets;
    }
}
