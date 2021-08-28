package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Getter
public class TicketServiceImpl implements TicketService {
    private ParkingService parkingService;
    private JSONArray ticketArray;
    private TicketDto ticketDto;
    private static final String TICKET_DATA_FILE_NAME = "parking-ms/src/main/resources/ticketData.json";
    private JSONWriter jsonWriter;
    private long parkingSlot;


    public TicketServiceImpl(ParkingService parkingService) {
        this.parkingService = parkingService;
        ticketArray = new JSONReader().getJsonArr(TICKET_DATA_FILE_NAME);
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

    @Override
    public long getParkingSlot()
    {
        return parkingSlot;
    }
    @Override
    public long getTicketID() {
        return ticketDto.getUuid();
    }

}
