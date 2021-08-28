package com.delphi.nice.training.service;

import org.json.simple.JSONObject;

public interface TicketService {
    boolean generateTicket();
    void removeTicket(JSONObject jsonObject);
}
