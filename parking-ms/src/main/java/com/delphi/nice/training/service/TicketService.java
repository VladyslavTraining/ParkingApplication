package com.delphi.nice.training.service;

import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketService {

    private final TicketDto ticketDto = new TicketDto();
    private JSONArray ticketArray = new JSONArray();
    private static final String TICKET_DATA_FILE_NAME = "ticketData.json";
    public TicketService() {
        if (!new File(TICKET_DATA_FILE_NAME).exists()) {
            new File(TICKET_DATA_FILE_NAME);
        } else {
            ticketArray = new JSONReader().getJsonArr(TICKET_DATA_FILE_NAME);
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
        try (FileWriter fw = new FileWriter(TICKET_DATA_FILE_NAME)) {
            ticketArray.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    JSONObject searchTicket()
//    {
//        for(Object o : ticketArray)
//        {
//            return (JSONObject) o;
//        }
//        return null;
//    }
    void removeTicket(JSONObject jsonObject)
    {
//        if(searchTicket().equals(jsonObject))
            ticketArray.remove(jsonObject);
            writeToFile();
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
