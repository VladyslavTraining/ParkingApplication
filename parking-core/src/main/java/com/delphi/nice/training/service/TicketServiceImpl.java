package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.TicketServiceValidator;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Getter
@Service
public class TicketServiceImpl implements TicketService {

    private final ParkingService parkingService;
    private final String ticketDataFileName;
    private final List<JSONObject> ticketArray;
    private TicketDto ticketDto;
    private final JSONWriter jsonWriter;
    private long parkingSlot;


//    @Autowired
    public TicketServiceImpl(ParkingService parkingService, @Value("${path.ticket}") String filename) {
        new TicketServiceValidator().validate(filename);
        ticketDataFileName = filename;
        this.parkingService = parkingService;
        ticketArray = new JSONReader().getJsonArr(ticketDataFileName);
        jsonWriter = new JSONWriter(ticketArray, ticketDataFileName);
    }

    @Override
    public boolean generateTicket() {

        try {
            parkingSlot = parkingService.park();
            ticketDto = new TicketDto();
            HashMap<String, Object> ticketFields = new HashMap<>();
            ticketFields.put("uuid", ticketDto.getUuid());
            ticketFields.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            ticketFields.put("parkingSlot", parkingSlot);
            ticketArray.add(new JSONObject(ticketFields));
            jsonWriter.writeToFile();
            log.info("New car entered \n" + ticketFields + "\n--------------------------------");
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public long getParkingSlot() {
        return parkingSlot;
    }

    @Override
    public List<JSONObject> getAllTickets() {
        return new JSONReader().getJsonArr(ticketDataFileName);
    }

    @Override
    public long getTicketID() {
        return ticketDto.getUuid();
    }

}
