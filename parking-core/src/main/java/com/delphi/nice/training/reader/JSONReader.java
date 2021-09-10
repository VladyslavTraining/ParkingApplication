package com.delphi.nice.training.reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSONReader implements Reader {

    @Override
    @SuppressWarnings("unchecked")
    public List<JSONObject> getJsonArr(String filepath) {
        JSONParser jsonParser = new JSONParser();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8)) {
            Object obj = jsonParser.parse(reader);
            return (List<JSONObject>) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}

