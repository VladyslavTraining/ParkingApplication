package com.delphi.nice.training.service;

import com.delphi.nice.training.model.TicketDto;
import com.delphi.nice.training.service.readers.ParkingSlotReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;


public class ExitService {

    private static JSONArray ticketArray = new JSONArray();

    public ExitService() {
        ticketArray = new ParkingSlotReader().getJsonArr("ticketData.json");
    }

    public void amountForPay(long id) {
        for (int i = 0; i < ticketArray.size(); i++) {
            long uuid = (long) ((JSONObject) ticketArray.get(i)).get("uuid");
            if (id == uuid) {
                String time = (String) ((JSONObject) ticketArray.get(i)).get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
                String correctAmount = String.format("%.2f", cost);
                System.out.println("Need to pay ---> " + correctAmount + "$");
                break;
            }
        }
    }

    private static long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }


}