package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class ParkingServiceImpl implements ParkingService {

    private JSONObject jsonObject;
    private final JSONArray jsonArray;
    private static final String IS_PARKED_FIELD = "isParked";
    private static final String PARKING_SLOT_FIELD = "parkingSlot";
    private static final String PARKING_AREA_FILE_PATH = "parking-ms/src/main/resources/parkingArea.json";

    public ParkingServiceImpl() {
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
