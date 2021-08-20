package com.delphi.nice.training.service;

import com.delphi.nice.training.model.TicketDto;
import com.delphi.nice.training.service.readers.ParkingSlotReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketService {

    private final TicketDto ticketDto = new TicketDto();
    private static JSONArray ticketArray;

    public TicketService() {
        if (!new File("ticketData.json").exists()) {
            new File("ticketData.json");
        } else {
            ticketArray = new ParkingSlotReader().getJsonArr("ticketData.json");
        }
    }

    public void generateTicket() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", ticketDto.getUuid());
        jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
        jsonObject.put("parkingSlot", new ParkingService().park());
        ticketArray.add(jsonObject);
        writeToFile();
        System.out.println("Your ID " + jsonObject.get("uuid").toString());
    }

    private void writeToFile() {
        try (FileWriter fw = new FileWriter("ticketData.json")) {
            ticketArray.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
