package com.delphi.nice.training.service;

import com.delphi.nice.training.model.ParkingSlotDto;
import com.delphi.nice.training.service.readers.ParkingSlotReader;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class ParkingService {

    private static final String PARKING_AREA_FILE_PATH = "parkingArea.json";
    private final JSONArray jsonArray;

    public ParkingService() {
        this.jsonArray = new ParkingSlotReader().getJsonArr(PARKING_AREA_FILE_PATH);
    }

    public int park() {
        return searchForFreePlaces();
    }

    public void showFreePlaces() {
        for (int i = 0; i < jsonArray.size(); i++) {
            String parkingSlotDto = String.valueOf(jsonArray.get(i));
            ParkingSlotDto dto = new Gson().fromJson(parkingSlotDto, ParkingSlotDto.class);
            if (!dto.isParked()) {
                System.out.println("Free spot is ---> " + dto.getParkingSpot());
            }
        }
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
}
