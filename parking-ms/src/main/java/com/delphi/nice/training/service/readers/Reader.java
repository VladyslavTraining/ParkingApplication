package com.delphi.nice.training.service.readers;

import com.google.gson.Gson;
import org.json.simple.JSONArray;

public interface Reader {
    JSONArray getJsonArr(String filepath);
}
