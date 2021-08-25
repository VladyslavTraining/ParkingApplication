package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;


public class ExitService {

    private final JSONArray ticketArray;
    private JSONObject exitVehicle;

    public ExitService() {
        ticketArray = new JSONReader().getJsonArr("ticketData.json");
    }

    private void amountForPay(long id) {
        for (Object o : ticketArray) {
            long uuid = (long) ((JSONObject) o).get("uuid");
            if (id == uuid) {
                String time = (String) ((JSONObject) o).get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
                String correctAmount = String.format("%.2f", cost);
                System.out.println("Need to pay ---> " + correctAmount + "$");
                exitVehicle = (JSONObject) o;
            }
        }
    }

    private static long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }

    public void exit(long id) {
        amountForPay(id);
        new ParkingService().leaveParking(exitVehicle);
        new TicketService().removeTicket(exitVehicle);
    }

}