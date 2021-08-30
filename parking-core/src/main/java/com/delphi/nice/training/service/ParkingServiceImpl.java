package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class ParkingServiceImpl implements ParkingService {

    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private static final String IS_PARKED_FIELD = "isParked";
    private static final String PARKING_SLOT_FIELD = "parkingSlot";
    private String PARKING_AREA_FILE_PATH;

    public ParkingServiceImpl(@Value("${path.parking}") String filepath) {
        PARKING_AREA_FILE_PATH = filepath;
        this.jsonArray = new JSONReader().getJsonArr(PARKING_AREA_FILE_PATH);
    }

    public long park() {
        if (takeFreeParkSpot()) {
            updateParking();
            return (long) jsonObject.get(PARKING_SLOT_FIELD);
        }
        throw new RuntimeException();
    }

    public boolean isFreeSlotPresent() {
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (!(boolean) jsonObject.get(IS_PARKED_FIELD))
                return true;
        }
        return false;
    }

    private boolean takeFreeParkSpot() {
        for (Object o : jsonArray) {
            jsonObject = (JSONObject) o;
            if (!(boolean) jsonObject.get(IS_PARKED_FIELD)) {
                jsonObject.replace(IS_PARKED_FIELD, true);
                return true;
            }
        }
        return false;
    }

    private void updateParking() {
        try (FileWriter fileWriter = new FileWriter(PARKING_AREA_FILE_PATH)) {
            jsonArray.writeJSONString(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
