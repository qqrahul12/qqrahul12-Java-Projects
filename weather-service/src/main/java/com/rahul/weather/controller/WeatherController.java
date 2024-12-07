package com.rahul.weather.controller;

import com.rahul.weather.service.OpenWeatherService;
import org.springframework.stereotype.Controller;

@Controller
public class WeatherController {
    private final OpenWeatherService openWeatherService;

    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }
}
