package com.delphi.nice.training.service;

import com.delphi.nice.training.reader.JSONReader;
import com.delphi.nice.training.validator.ParkAreaValidator;
import com.delphi.nice.training.writer.JSONWriter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private JSONObject jsonObject;
    private List<JSONObject> jsonArray;
    private static final String IS_PARKED_FIELD = "isParked";
    private static final String PARKING_SLOT_FIELD = "parkingSlot";
    private final String parkingAreaFilePath;

    public ParkingServiceImpl(@Value("${path.parking}") String filepath) {
        parkingAreaFilePath = filepath;
        new ParkAreaValidator().validate(filepath);
        jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
    }

    @Override
    public long park() {
        if (takeFreeParkSpot()) {
            updateParking();
            return (long) jsonObject.get(PARKING_SLOT_FIELD);
        }
        throw new IndexOutOfBoundsException();
    }

    public boolean isFreeSlotPresent() {
        for (JSONObject o : jsonArray) {
            jsonObject = o;
            if (!(boolean) jsonObject.get(IS_PARKED_FIELD))
                return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private boolean takeFreeParkSpot() {
        for (JSONObject o : jsonArray) {
            jsonObject = o;
            if (!(boolean) jsonObject.get(IS_PARKED_FIELD)) {
                jsonObject.replace(IS_PARKED_FIELD, true);
                return true;
            }
        }
        return false;
    }

    private void updateParking() {
        new JSONWriter(jsonArray, parkingAreaFilePath).writeToFile();
        this.jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
    }

}
