package com.rahul.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Repository
public class OpenWeatherClient {

    @Value("${open-weather.api-key}")
    private String apiKey;

    @Value("${open-weather.url}")
    private String url;

    public String getForecast(String city) throws IOException {
        String endpoint = url + "?q=" + URLEncoder.encode(city, StandardCharsets.UTF_8) + "&appid=" + apiKey + "&units=metric";
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}
