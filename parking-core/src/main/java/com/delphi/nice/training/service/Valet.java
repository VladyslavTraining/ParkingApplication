package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import org.json.simple.JSONObject;

import java.util.List;

public interface Valet {
    TicketDto parkTheCar();
    String exitTheCar(long uuid);
    TicketDto getTicketById(long uuid);
    List<JSONObject> getAllTickets();
}
