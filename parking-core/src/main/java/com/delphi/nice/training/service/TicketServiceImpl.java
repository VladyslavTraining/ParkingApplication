package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Getter
public class TicketServiceImpl implements TicketService {
    private ParkingServiceImpl parkingServiceImpl;
    private TicketDto ticketDto;
    private JSONArray ticketArray;
    private static final String TICKET_DATA_FILE_NAME = "parking-ms/src/main/resources/ticketData.json";
    JSONWriter jsonWriter;
    private long parkingSlot;


    public TicketServiceImpl(ParkingServiceImpl parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
        ticketArray = new JSONReader().getJsonArr(TICKET_DATA_FILE_NAME);
        jsonWriter = new JSONWriter(ticketArray, TICKET_DATA_FILE_NAME);
    }

    public boolean generateTicket() {
        if (parkingServiceImpl.isFreeSlotPresent()) {
            ticketDto = new TicketDto();
            parkingSlot = parkingServiceImpl.park();
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

    public void removeTicket(JSONObject jsonObject) {
        ticketArray.remove(jsonObject);
        jsonWriter.writeToFile();
    }
}
