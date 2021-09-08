package com.delphi.nice.training.reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JSONReader implements Reader {

    @Override
    public List<JSONObject> getJsonArr(String filepath) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(filepath)) {
            Object obj = jsonParser.parse(reader);
            return (List<JSONObject>) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}

