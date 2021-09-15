package com.delphi.nice.training.service;


import com.delphi.nice.training.dto.TicketDto;
import org.json.simple.JSONObject;

import java.util.List;

public interface TicketService {
    //    boolean generateTicket();
    TicketDto createTicket();
    JSONObject getTicket(long id);
    //    long getParkingSlot();
    List<JSONObject> getAllTickets();
}
