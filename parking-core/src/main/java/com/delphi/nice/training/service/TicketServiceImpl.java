package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
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


    public TicketServiceImpl(@Value("${path.ticket}") String filename) {
        ticketDataFileName = filename;
        if(new TicketServiceValidator().validate(filename)) {
            ticketArray = new JSONReader().getJsonArr(filename);
        }
        jsonWriter = new JSONWriter(filename);
    }

    @Override
    public TicketDto createTicket() {
        String username;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            updateTicketData();
            ticketDto = new TicketDto();
            HashMap<String, Object> ticketFields = new HashMap<>();
            ticketFields.put("uuid", ticketDto.getUuid());
            ticketFields.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            ticketFields.put("user", username);
            ticketArray.add(new JSONObject(ticketFields));
            new JSONWriter(ticketDataFileName).writeToFile(ticketArray);
            log.info("New car entered {}", ticketFields);
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
        throw new IllegalArgumentException("No such ticket with id " + id);
    }

    private void updateTicketData() {
        this.ticketArray = new JSONReader().getJsonArr(ticketDataFileName);
    }
}
