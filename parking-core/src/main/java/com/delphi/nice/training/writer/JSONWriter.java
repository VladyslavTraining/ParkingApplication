package com.delphi.nice.training.writer;

import org.json.simple.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        try (OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filepath), StandardCharsets.UTF_8)) {
            fw.write('[');
            for (JSONObject object : jsonArray) {
                object.writeJSONString(fw);
                if (jsonArray.size() > 1) {
                    fw.write(',');
                }
            }
            fw.write(']');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
