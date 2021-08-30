package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ExitServiceImpl implements ExitService {
    private JSONArray ticketArray;
    private JSONArray parkingArray;
    private JSONObject exitVehicle;
    @Value("${path.ticket}")
    private String TICKET_DATA_PATH;
    @Value("${path.parking}")
    private String PARKING_AREA_PATH;

    private String amountForPay(long id) {
        ticketArray = new JSONReader().getJsonArr(TICKET_DATA_PATH);
        parkingArray = new JSONReader().getJsonArr(PARKING_AREA_PATH);
        for (Object o : ticketArray) {
            long uuid = (long) ((JSONObject) o).get("uuid");
            if (id == uuid) {
                String time = (String) ((JSONObject) o).get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
//                System.out.printf("Need to pay ---> %.2f$%s", cost, System.lineSeparator());
                exitVehicle = (JSONObject) o;
                return String.format("Need to pay ---> %.2f$%s", cost, System.lineSeparator());
            }
        }
        return null;
    }

    private long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }

    @Override
    public boolean exit(long id) {
        String payMessage = amountForPay(id);
        if (payMessage == null)
            return false;
        ticketArray.remove(exitVehicle);
        exitVehicle = (JSONObject) parkingArray.get(Integer.parseInt(exitVehicle.get("parkingSlot").toString()) - 1);
        exitVehicle.replace("isParked", false);
        new JSONWriter(ticketArray, TICKET_DATA_PATH).writeToFile();
        new JSONWriter(parkingArray, PARKING_AREA_PATH).writeToFile();
        System.out.println(payMessage);
        return true;
    }

}