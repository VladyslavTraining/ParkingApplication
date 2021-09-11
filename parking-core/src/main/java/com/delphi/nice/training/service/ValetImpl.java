package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.writer.JSONWriter;
import com.delphi.nice.training.writer.Writer;
import org.springframework.stereotype.Service;

@Service
public class ValetImpl implements Valet {
    ParkingService parkingService;
    ExitService exitService;
    TicketService ticketService;

    public ValetImpl(TicketService ticketService, ParkingService parkingService, ExitService exitService) {
        this.parkingService = parkingService;
        this.exitService = exitService;
        this.ticketService = ticketService;
    }

    @Override
    public TicketDto parkTheCar() {
        if (parkingService.isFreeSlotPresent()) {
            parkingService.takeFreeParkSpot();
            return ticketService.createTicket();
        }
        throw new RuntimeException();
    }

    @Override
    public String exitTheCar(long uuid) {
        if(exitService.exit(uuid))
            return exitService.getPayMessage();
        throw new RuntimeException();
    }

    @Override
    public TicketDto getTicketById(long uuid) {
        return ticketService.getTicket(uuid);
    }

}
