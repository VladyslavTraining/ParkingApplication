package com.delphi.nice.training.writer;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWriter implements Writer {
    private final List<JSONObject> jsonArray;
    private final String filepath;

    public JSONWriter(List<JSONObject> array, String filepath) {
        this.filepath = filepath;
        this.jsonArray = array;
    }

    @Override
    public void writeToFile() {
        try (FileWriter fw = new FileWriter(filepath)) {
            fw.write('[');
            for (JSONObject object: jsonArray) {
                object.writeJSONString(fw);
                if(jsonArray.size()>1) {
                    fw.write(',');
                }
            }
            fw.write(']');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
