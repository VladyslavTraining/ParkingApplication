package com.delphi.nice.training.writer;

import org.json.simple.JSONObject;

import java.util.List;

public interface Writer {
    void writeToFile(List<JSONObject> jsonObject);
}
