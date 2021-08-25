package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class ParkingService {

    private static final String PARKING_AREA_FILE_PATH = "parking-ms/src/main/resources/parkingArea.json";
    private final JSONArray jsonArray;

    public ParkingService() {
        this.jsonArray = new JSONReader().getJsonArr(PARKING_AREA_FILE_PATH);
    }

    public int park() {
        for (int i = 0; i < jsonArray.size(); i++) {
            if (takeFreeParkSpot((JSONObject) jsonArray.get(i))) {
                System.out.println(jsonArray.get(i));
                updateParking();
                return i+1;
            }
        }
        throw new RuntimeException();
    }

    public boolean isFreeSlotPresent() {
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            if (!(boolean) jsonObject.get("isParked"))
                return true;
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

    private boolean takeFreeParkSpot(JSONObject object) {
        if (!(boolean) object.get("isParked")) {
            object.replace("isParked", true);
            return true;
        }
        return false;
    }
    private void leave(JSONObject object)
    {
            object.replace("isParked", false);
    }
    void leaveParking(JSONObject object)
    {
        int parkPlace = Integer.parseInt(object.get("parkingSlot").toString());
        leave((JSONObject) jsonArray.get(parkPlace));
        updateParking();
    }
}
