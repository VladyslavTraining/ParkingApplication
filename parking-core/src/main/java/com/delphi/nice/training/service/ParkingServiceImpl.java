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
    private List<JSONObject> jsonArray;
    private static final String IS_PARKED_FIELD = "isParked";
    private final String parkingAreaFilePath;

    public ParkingServiceImpl(@Value("${path.parking}") String filepath) {
        parkingAreaFilePath = filepath;
        new ParkAreaValidator().validate(filepath);
        jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
    }

    @Override
    public boolean isFreeSlotPresent() {
        for (JSONObject o : jsonArray) {
            if (!(boolean) o.get(IS_PARKED_FIELD))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void takeFreeParkSpot() {
        for (JSONObject o : jsonArray) {
            if (!(boolean) o.get(IS_PARKED_FIELD)) {
                o.replace(IS_PARKED_FIELD, true);
                updateParking();
                break;
            }
        }
    }

    private void updateParking() {
        new JSONWriter( parkingAreaFilePath).writeToFile(jsonArray);
        this.jsonArray = new JSONReader().getJsonArr(parkingAreaFilePath);
    }

}
