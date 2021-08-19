package com.delphi.nice.training.service;

import com.delphi.nice.training.service.readers.ParkingSlotReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class ParkingService {
    private static final String PARKING_AREA_FILE_PATH = "parkingArea.json";
    private final JSONArray jsonArray;
    public ParkingService(){
        this.jsonArray = new ParkingSlotReader().getJsonArr(PARKING_AREA_FILE_PATH);
    }
    public int park() {
        return searchForFreePlaces();
    }

    private int searchForFreePlaces() {
        for (int i = 0; i < jsonArray.size(); i++) {
            if (takeFreeParkSpot((JSONObject) jsonArray.get(i))) {
                System.out.println(jsonArray.get(i));
                updateParking(jsonArray);
                return i;
            }
        }
        throw new RuntimeException();
    }

    private void updateParking(JSONArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(PARKING_AREA_FILE_PATH)){
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
}
