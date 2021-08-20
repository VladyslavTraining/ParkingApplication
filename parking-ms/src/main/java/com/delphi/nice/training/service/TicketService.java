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
    private static JSONArray ticketArray = new JSONArray();

    public TicketService() {
        if (!new File("ticketData.json").exists()) {
            new File("ticketData.json");
        } else {
            ticketArray = new ParkingSlotReader().getJsonArr("ticketData.json");
        }
    }

    public String generateTicket() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", ticketDto.getUuid());
        jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
        jsonObject.put("parkingSlot", new ParkingService().park());
        ticketArray.add(jsonObject);
        writeToFile();
        return jsonObject.toJSONString();
    }

    private void writeToFile() {
        try (FileWriter fw = new FileWriter("ticketData.json")) {
            ticketArray.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public String generateTicket() {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("uuid", ticketDto.getUuid());
//        jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
//        jsonObject.put("parkingSlot", new ParkingService().park());
//        try (FileWriter fw = new FileWriter("ticketData.json")) {
//            jsonObject.writeJSONString(fw);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject.toJSONString();
//    }
}
