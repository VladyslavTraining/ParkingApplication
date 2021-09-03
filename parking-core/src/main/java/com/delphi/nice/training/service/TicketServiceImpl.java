package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.TicketServiceValidator;
import com.delphi.nice.training.validator.Validator;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Component
public class TicketServiceImpl implements TicketService {

    private final ParkingService parkingService;

    private final String ticketDataFileName;
    private final JSONArray ticketArray;
    private TicketDto ticketDto;
    private final JSONWriter jsonWriter;
    private long parkingSlot;


    @Autowired
    public TicketServiceImpl(ParkingService parkingService, @Value("${path.ticket}") String filename) {
        new TicketServiceValidator().validate(filename);
        ticketDataFileName = filename;
        this.parkingService = parkingService;
        ticketArray = new JSONReader().getJsonArr(ticketDataFileName);
        jsonWriter = new JSONWriter(ticketArray, ticketDataFileName);
    }

    public boolean generateTicket() {

        if (parkingService.isFreeSlotPresent()) {

            ticketDto = new TicketDto();
            parkingSlot = parkingService.park();
            HashMap<String, Object> ticketFields = new HashMap<>();
            ticketFields.put("uuid", ticketDto.getUuid());
            ticketFields.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            ticketFields.put("parkingSlot", parkingSlot);
            ticketArray.add(new JSONObject(ticketFields));
            jsonWriter.writeToFile();
            return true;
        }
        System.out.println("All parking spot is busy");
        return false;
    }

    @Override
    public long getParkingSlot() {
        return parkingSlot;
    }

    @Override
    public long getTicketID() {
        return ticketDto.getUuid();
    }

}
