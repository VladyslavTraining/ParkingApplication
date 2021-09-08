package com.delphi.nice.training.service;


import org.json.simple.JSONObject;

import java.util.List;

public interface TicketService {
    boolean generateTicket();
    long getTicketID();
    long getParkingSlot();
    List<JSONObject> getAllTickets();
}
