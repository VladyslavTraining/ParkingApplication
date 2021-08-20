package com.delphi.nice.training.reader;

import org.json.simple.JSONArray;

public interface Reader {
    JSONArray getJsonArr(String filepath);
}
