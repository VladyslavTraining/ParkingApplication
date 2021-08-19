package com.delphi.nice.training.service.readers;

import com.delphi.nice.training.model.ParkingSlotDto;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ParkingSlotReader implements Reader {

    @Override
    public JSONArray getJsonArr(String filepath) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filepath)) {
            Object obj = jsonParser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}

