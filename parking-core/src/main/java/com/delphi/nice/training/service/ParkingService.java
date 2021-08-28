package com.delphi.nice.training.service;

import org.json.simple.JSONObject;

public interface ParkingService {
    long park();
    boolean isFreeSlotPresent();
    void leaveParking(JSONObject object);
}
