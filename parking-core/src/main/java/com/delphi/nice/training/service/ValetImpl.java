package com.delphi.nice.training.service;

import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.ticket.Ticket;
import com.delphi.nice.training.ticket.TicketDao;
import com.delphi.nice.training.writer.JSONWriter;
import com.delphi.nice.training.writer.Writer;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValetImpl implements Valet {

    private final ExitService exitService;
    private final TicketDao ticketService;
    @Value("${path.ticket}")
    private String filePath;
    @Value("${car.threshold}")
    private int carThreshold;



    @Override
    public Ticket parkTheCar() {
        List<JSONObject> tickets = new JSONReader().getJsonArr(filePath);
        if (tickets.size()<carThreshold) {
            Writer writer = new JSONWriter(filePath);
            HashMap<String, Object> ticket = new HashMap<>();
            Ticket ticketDao = new Ticket();
            ticket.put("id", tickets.size()+1);
            ticket.put("uuid", ticketDao.getUuid());
            ticket.put("entranceTime", ticketDao.getEntranceDateTime().toString());
            ticket.put("isValid", ticketDao.isValid());
            tickets.add(new JSONObject(ticket));
            writer.writeToFile(tickets);
            return ticketDao;
        }
        throw new IllegalStateException("All parking slots is busy!");
    }

    @Override
    public String exitTheCar(long uuid) {
        if (exitService.exit(uuid))
            return exitService.getPayMessage();
        throw new UserNotFoundException(uuid);
    }

    @Override
    public Ticket getTicketById(long uuid) {
        return ticketService.selectTicketByUuid(uuid);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

}
