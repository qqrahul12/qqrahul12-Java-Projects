package com.rahul.weather.service;

import com.rahul.weather.client.OpenWeatherClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenWeatherService {
    private final OpenWeatherClient openWeatherClient;

    public OpenWeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    public String getWeather(String city) throws IOException {
        return openWeatherClient.getForecast(city);
    }
}
