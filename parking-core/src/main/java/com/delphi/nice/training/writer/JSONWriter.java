package com.delphi.nice.training.writer;

import org.json.simple.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSONWriter implements Writer {
    private final String filepath;

    public JSONWriter(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void writeToFile(List<JSONObject> jsonArray) {
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
