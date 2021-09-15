package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.TicketServiceValidator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Getter
@Service
public class TicketServiceImpl implements TicketService {

    private final String ticketDataFileName;
    private List<JSONObject> ticketArray;
    private TicketDto ticketDto;
    public TicketServiceImpl(@Value("${path.ticket}") String filename) {
        ticketDataFileName = filename;
        if (new TicketServiceValidator().validate(filename)) {
            ticketArray = new JSONReader().getJsonArr(filename);
        }
    }

    @Override
    public TicketDto createTicket() {
        try {
            updateTicketData();
            ticketDto = new TicketDto();
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
    public JSONObject getTicket(long id) {
        updateTicketData();
        return ticketArray.stream()
                .filter(object -> object.containsValue(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void updateTicketData() {
        this.ticketArray = new JSONReader().getJsonArr(ticketDataFileName);
    }
}
