package com.delphi.nice.training.writer;

import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
    private final JSONArray jsonArray;
    private final String filepath;

    public JSONWriter(JSONArray array, String filepath) {
        this.filepath = filepath;
        this.jsonArray = array;
    }

    public void writeToFile() {
        try(FileWriter fw = new FileWriter(filepath)) {
            jsonArray.writeJSONString(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
