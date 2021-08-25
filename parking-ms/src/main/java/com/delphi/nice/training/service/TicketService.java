package com.delphi.nice.training.service;

import com.delphi.nice.training.model.dto.ClientCardDto;
import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketService {
    private ClientCardDto clientCardDto;
    private final TicketDto ticketDto = new TicketDto();
    private JSONArray ticketArray = new JSONArray();
    private static final String TICKET_DATA_FILE_NAME = "parking-ms/src/main/resources/ticketData.json";

    public TicketService() {
        if (!new File(TICKET_DATA_FILE_NAME).exists()) {
            new File(TICKET_DATA_FILE_NAME);
        } else {
            ticketArray = new JSONReader().getJsonArr(TICKET_DATA_FILE_NAME);
        }
    }

    public TicketDto getTicket() {
        return ticketDto;
    }

    public boolean generateTicket() {
        ParkingService parkingService = new ParkingService();
        if (parkingService.isFreeSlotPresent()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", ticketDto.getUuid());
            jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            jsonObject.put("parkingSlot", parkingService.park());
            ticketArray.add(jsonObject);
            writeToFile();
            return true;
        }
        System.out.println("All parking spot is busy");
        return false;
    }

    private void writeToFile() {
        try (FileWriter fw = new FileWriter(TICKET_DATA_FILE_NAME)) {
            ticketArray.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void removeTicket(JSONObject jsonObject) {
        ticketArray.remove(jsonObject);
        writeToFile();
    }
}
