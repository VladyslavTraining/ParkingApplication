package com.delphi.nice.training.reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface Reader {
    List<JSONObject> getJsonArr(String filepath);
}
