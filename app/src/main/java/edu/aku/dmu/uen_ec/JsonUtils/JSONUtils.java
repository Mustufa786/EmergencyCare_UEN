package edu.aku.dmu.uen_ec.JsonUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtils {

    public static <T> T getModelFromJSON(String json, Class<T> type) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, type);
    }
    public static <T> String getJSONFromModel(Object obj, Class<T> type) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj, type);
    }

}
