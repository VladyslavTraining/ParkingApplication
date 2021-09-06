package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.ParkAreaValidator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class ParkingServiceImpl implements ParkingService {
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private static final String IS_PARKED_FIELD = "isParked";
    private static final String PARKING_SLOT_FIELD = "parkingSlot";
    private final String parkingAreaFilePath;

    public ParkingServiceImpl(@Value("${path.parking}") String filepath) {
        parkingAreaFilePath = filepath;
        new ParkAreaValidator().validate(filepath);
    }

    @Override
    public long park() {
        this.jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
        if (takeFreeParkSpot()) {
            updateParking();
            return (long) jsonObject.get(PARKING_SLOT_FIELD);
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean isFreeSlotPresent() {
        this.jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
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
        try (FileWriter fileWriter = new FileWriter(parkingAreaFilePath)) {
            jsonArray.writeJSONString(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
