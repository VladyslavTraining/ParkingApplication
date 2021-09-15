package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.reader.JSONReader;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValetImpl implements Valet {

    private final ParkingService parkingService;
    private final ExitService exitService;
    private final TicketService ticketService;
    @Value("${path.ticket}")
    String filePath;
    String username;

    @Override
    public TicketDto parkTheCar() {
        checkUserPresence();
        if (parkingService.isFreeSlotPresent()) {
            parkingService.takeFreeParkSpot();
            return ticketService.createTicket();
        }
        throw new IllegalStateException("All parking slots is busy!");
    }

    @Override
    public String exitTheCar(long uuid) {
        if (exitService.exit(uuid))
            return exitService.getPayMessage();
        throw new IllegalStateException("No such ticket with uuid " + uuid);
    }

    @Override
    public TicketDto getTicketById(long uuid) {
        return ticketService.getTicket(uuid);
    }

    @Override
    public JSONObject getTicketByUsername(String username) {
        initUser();
        return getAllTickets().stream()
                .filter(jsonObject -> jsonObject.containsValue(username) && username.equals(this.username))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
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
