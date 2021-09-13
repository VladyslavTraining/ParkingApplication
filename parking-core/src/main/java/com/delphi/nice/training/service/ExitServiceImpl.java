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
    private List<JSONObject> parkingArray;
    private JSONObject exitVehicle;
    private String payMessage;
    private final String ticketDataPath;
    private final String parkingAreaPath;

    public ExitServiceImpl(@Value("${path.ticket}") String ticketDataPath, @Value("${path.parking}") String parkingAreaPath) {
        this.ticketDataPath = ticketDataPath;
        this.parkingAreaPath = parkingAreaPath;
    }

    private String amountForPay(long id) {
        ticketArray = new JSONReader().getJsonArr(ticketDataPath);
        parkingArray = new JSONReader().getJsonArr(parkingAreaPath);
        for (JSONObject o : ticketArray) {
            long uuid = (long) o.get("uuid");
            if (id == uuid) {
                String time = (String) o.get("entranceTime");
                LocalDateTime enter = LocalDateTime.parse(time);
                LocalDateTime exit = LocalDateTime.now();
                long seconds = getTime(enter, exit);
                double cost = seconds * 0.001;
                exitVehicle = o;
                return String.format("Need to pay ---> %.2f$%s", cost, System.lineSeparator());
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
        ticketArray.remove(exitVehicle);
        log.info("Car leave the parking \n" + exitVehicle + "\n" + payMessage);
        for (JSONObject object : parkingArray) {
            if ((boolean) object.get("isParked")) {
                object.replace("isParked", false);
                break;
            }
        }
        new JSONWriter(ticketArray, ticketDataPath).writeToFile();
        new JSONWriter(parkingArray, parkingAreaPath).writeToFile();
        return true;
    }

    @Override
    public String getPayMessage() {
        return this.payMessage;
    }

}