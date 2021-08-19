package com.delphi.nice.training.service;

import com.delphi.nice.training.model.TicketDto;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class TicketService {
    TicketDto ticketDto = new TicketDto();

    public String generateTicket() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", ticketDto.getUuid());
        jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
        jsonObject.put("parkingSlot", new ParkingService().park());
        try(FileWriter fw = new FileWriter("ticketData.json")) {
            jsonObject.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.toJSONString();
    }
}
