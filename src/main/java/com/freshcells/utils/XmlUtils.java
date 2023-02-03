package com.freshcells.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.List;
import java.util.stream.Collectors;

public class XmlUtils {

    private static String toJson(String xml) {
        try {
            JSONObject json = XML.toJSONObject(xml);
            return json.toString(4);
        } catch (JSONException e) {
            System.out.println("Unable to convert xml to json: " + e.toString());
        }

        return "";
    }

    public static List<String> toListJson(List<String> xmlList) {
        return xmlList.stream().map(XmlUtils::toJson).collect(Collectors.toList());
    }
}
