package com.delphi.nice.training.service;


import com.delphi.nice.training.dto.TicketDto;

import java.util.List;

public interface TicketService {
    boolean generateTicket();
    long getTicketID();
    long getParkingSlot();
    List<TicketDto> getAllTickets();
}
