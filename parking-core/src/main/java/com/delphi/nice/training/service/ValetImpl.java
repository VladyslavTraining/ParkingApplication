package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.exception.UserNotFoundException;
import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import com.delphi.nice.training.writer.Writer;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValetImpl implements Valet {

    private final ParkingService parkingService;
    private final ExitService exitService;
    private final TicketService ticketService;
    @Value("${path.ticket}")
    private String filePath;
    private String username;


    @Override
    public TicketDto parkTheCar() {
        checkUserPresence();
        if (parkingService.isFreeSlotPresent()) {
            List<JSONObject> tickets = new JSONReader().getJsonArr(filePath);
            Writer writer = new JSONWriter(filePath);
            HashMap<String, Object> ticket = new HashMap<>();
            parkingService.takeFreeParkSpot();
            TicketDto ticketDto = ticketService.createTicket();
            ticket.put("uuid", ticketDto.getUuid());
            ticket.put("entranceTime", ticketDto.getEntranceDateTime().toString());
            ticket.put("user", username);
            tickets.add(new JSONObject(ticket));
            writer.writeToFile(tickets);
            return ticketDto;
        }
        throw new IllegalStateException("All parking slots is busy!");
    }

    @Override
    public String exitTheCar(long uuid) {
        if (exitService.exit(uuid))
            return exitService.getPayMessage();
        throw new UserNotFoundException(uuid);
    }

    @Override
    public JSONObject getTicketById(long uuid) {
        initUser();
        return getAllTickets().stream()
                .filter(jsonObject -> jsonObject.containsValue(uuid) && jsonObject.containsValue(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(uuid));
    }

    @Override
    public JSONObject getTicketByUsername(String username) {
        initUser();
        return getAllTickets().stream()
                .filter(jsonObject -> jsonObject.containsValue(username) && username.equals(this.username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public List<JSONObject> getAllTickets() {
        return ticketService.getAllTickets();
    }

    private void checkUserPresence() {
        initUser();
        for (JSONObject object : new JSONReader().getJsonArr(filePath)) {
            if (object.containsValue(username))
                throw new IllegalStateException("The ticket for this user already exists");
        }
    }

    private void initUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
    }
}
