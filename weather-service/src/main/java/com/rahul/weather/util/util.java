package com.rahul.weather.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class util {
    public static void parseAndPrintResponse(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        if (jsonObject.getInt("cod") == 200) {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject forecast = jsonArray.getJSONObject(i);
                long timestamp = forecast.getLong("dt");
                String date = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        .format(Instant.ofEpochSecond(timestamp));
                double temperature = forecast.getJSONObject("main").getDouble("temp");
                double humidity = forecast.getJSONObject("main").getDouble("humidity");
                String description = forecast.getJSONArray("weather").getJSONObject(0).getString("description");
                System.out.println(date + ": " + temperature + "C, " + humidity + "%, " + description);
            }
        }
    }
}
