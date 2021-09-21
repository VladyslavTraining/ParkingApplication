package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.writer.JSONWriter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ExitServiceImpl implements ExitService {

    private List<JSONObject> ticketArray;
    private JSONObject exitVehicle;
    private String payMessage;
    private final String ticketDataPath;

    public ExitServiceImpl(@Value("${path.ticket}") String ticketDataPath) {
        this.ticketDataPath = ticketDataPath;
    }

    private String amountForPay(long id) {
        ticketArray = new JSONReader().getJsonArr(ticketDataPath);
        for (JSONObject o : ticketArray) {
            long uuid = (long) o.get("uuid");
            if (id == uuid) {
                String time = (String) o.get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
                exitVehicle = o;
                return String.format("Need to pay ---> %.2f$", cost);
            }
        }
        throw new IllegalStateException("There is no such ticket with uuid " + id);
    }

    private long getTime(LocalDateTime enter, LocalDateTime exit) {
        Duration duration = Duration.between(enter, exit);
        return duration.getSeconds();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exit(long id) {
        this.payMessage = amountForPay(id);
        if (this.payMessage == null)
            return false;
        exitVehicle.replace("isValid", false);
        log.info("Car leave the parking {},{}", exitVehicle, payMessage);
        updateParking();
        return true;
    }

    private void updateParking() {
        new JSONWriter(ticketDataPath).writeToFile(ticketArray);
    }

    @Override
    public String getPayMessage() {
        return this.payMessage;
    }

}