package com.freshcells.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    public static JSONObject mergeJson(JSONObject sourceJson, JSONObject targetJson) {
        for (String key: JSONObject.getNames(sourceJson)) {
            Object value = sourceJson.get(key);
            if (!targetJson.has(key)) {
                targetJson.put(key, value);
            } else {
                if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject)value;
                    mergeJson(valueJson, targetJson.getJSONObject(key));
                } else {
                    targetJson.put(key, value);
                }
            }
        }
        return targetJson;
    }

    public static JSONObject stringToJsonObject(String stringJson) {
        try {
            return new JSONObject(stringJson);
        } catch (JSONException e) {
            System.out.println("Unable to convert string json to json: " + e.toString());
        }

        return new JSONObject();
    }

    public static String mergeListJson(List<String> jsonList) {
        JSONObject currentJson = new JSONObject();
        for (String nextJson : jsonList) {
            currentJson = JsonUtils.mergeJson(JsonUtils.stringToJsonObject(nextJson), currentJson);
        }
        return currentJson.toString(4);
    }
}
