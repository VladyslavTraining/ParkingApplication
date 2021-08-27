package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;


public class ExitService {

    private JSONObject exitVehicle;
    ParkingService parkingService;
    TicketService ticketService;

    public ExitService(ParkingService parkingService, TicketService ticketService) {
        this.ticketService = ticketService;
        this.parkingService = parkingService;
    }

    private void amountForPay(long id) {
        JSONArray ticketArray = new JSONReader().getJsonArr("parking-ms/src/main/resources/ticketData.json");
        for (Object o : ticketArray) {
            long uuid = (long) ((JSONObject) o).get("uuid");
            if (id == uuid) {
                String time = (String) ((JSONObject) o).get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
                System.out.printf("Need to pay ---> %.2f$%s", cost, System.lineSeparator());
                exitVehicle = (JSONObject) o;
            }
        }
    }

    private long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }

    public void exit(long id) {
        amountForPay(id);
        parkingService.leaveParking(exitVehicle);
        ticketService.removeTicket(exitVehicle);
    }

}