package com.delphi.nice.training.service;

import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;

@Getter
public class TicketService {
    private ParkingService parkingService;
    private TicketDto ticketDto;
    private JSONArray ticketArray;
    private static final String TICKET_DATA_FILE_NAME = "parking-ms/src/main/resources/ticketData.json";
    JSONWriter jsonWriter;
    private long parkingSlot;


    public TicketService(ParkingService parkingService) {
        this.parkingService = parkingService;
        if(new File(TICKET_DATA_FILE_NAME).length()!=0)
            ticketArray = new JSONReader().getJsonArr(TICKET_DATA_FILE_NAME);
        else ticketArray = new JSONArray();
        jsonWriter = new JSONWriter(ticketArray, TICKET_DATA_FILE_NAME);
    }

    public boolean generateTicket() {
        if (parkingService.isFreeSlotPresent()) {
            ticketDto = new TicketDto();
            parkingSlot = parkingService.park();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", ticketDto.getUuid());
            jsonObject.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            jsonObject.put("parkingSlot", parkingSlot);
            ticketArray.add(jsonObject);
            jsonWriter.writeToFile();
            return true;
        }
        System.out.println("All parking spot is busy");
        return false;
    }

    void removeTicket(JSONObject jsonObject) {
        ticketArray.remove(jsonObject);
        jsonWriter.writeToFile();
    }
}
