package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValetImpl implements Valet {
    private final ParkingService parkingService;
    private final ExitService exitService;
    private final TicketService ticketService;

    @Override
    public TicketDto parkTheCar() {
        if (parkingService.isFreeSlotPresent()) {
            parkingService.takeFreeParkSpot();
            return ticketService.createTicket();
        }
        throw new IllegalStateException("All parking slots is busy!");
    }

    @Override
    public String exitTheCar(long uuid) {
        if(exitService.exit(uuid))
            return exitService.getPayMessage();
        throw new IllegalStateException("No such ticket with uuid "+ uuid);
    }

    @Override
    public TicketDto getTicketById(long uuid) {
        return ticketService.getTicket(uuid);
    }

    @Override
    public List<JSONObject> getAllTickets() {
        return ticketService.getAllTickets();
    }

}
