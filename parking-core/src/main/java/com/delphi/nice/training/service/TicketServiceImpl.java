package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.TicketServiceValidator;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Getter
@Service
public class TicketServiceImpl implements TicketService {

    private final String ticketDataFileName;
    private List<JSONObject> ticketArray;
    private TicketDto ticketDto;
    private final JSONWriter jsonWriter;
    private String username;

    public TicketServiceImpl(@Value("${path.ticket}") String filename) {
        ticketDataFileName = filename;
        if (new TicketServiceValidator().validate(filename)) {
            ticketArray = new JSONReader().getJsonArr(filename);
        }
        jsonWriter = new JSONWriter(filename);
    }

    public Long getId() {
        return ticketDto.getUuid();
    }

    @Override
    public TicketDto createTicket() {
        try {
            updateTicketData();
            ticketDto = new TicketDto();
//            HashMap<String, Object> ticketFields = new HashMap<>();
//            ticketFields.put("uuid", ticketDto.getUuid());
//            ticketFields.put("entranceTime", ticketDto.getEntranceDateTime().toString());
//            ticketArray.add(new JSONObject(ticketFields));
//            new JSONWriter(ticketDataFileName).writeToFile(ticketArray);
            log.info("New car entered {}", ticketDto);
            return ticketDto;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("");
        }
    }

    @Override
    public List<JSONObject> getAllTickets() {
        updateTicketData();
        return ticketArray;
    }

    @Override
    public TicketDto getTicket(long id) {
        updateTicketData();
        TicketDto ticket = new TicketDto();
        for (JSONObject object : ticketArray) {
            if ((long) object.get("uuid") == id) {
                ticket.setUuid(id);
                ticket.setEntranceDateTime(LocalDateTime.parse((String) object.get("entranceTime")));
                return ticket;
            }
        }
        throw new UserNotFoundException(id);
    }

    private void updateTicketData() {
        this.ticketArray = new JSONReader().getJsonArr(ticketDataFileName);
    }
}
