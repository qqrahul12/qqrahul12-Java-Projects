package com.rahul.weather;

import com.rahul.weather.client.OpenWeatherClient;
import com.rahul.weather.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Weather Prediction Application
 *
 */

@SpringBootApplication
@EnableConfigurationProperties(OpenWeatherConfiguration.class)
public class WeatherApplication implements CommandLineRunner
{
    @Autowired
    private final OpenWeatherConfiguration openWeatherConfiguration;

    @Autowired
    private final OpenWeatherService openWeatherService;

    @Autowired
    private final OpenWeatherClient openWeatherController;

    @Autowired
    private final OpenWeatherClient openWeatherClient;

    @Value("$(spring.application.name)")
    private String appName;

    public String getName() {
        return appName;
    }

    public WeatherApplication(OpenWeatherConfiguration openWeatherConfiguration, OpenWeatherService openWeatherService, OpenWeatherClient openWeatherController, OpenWeatherClient openWeatherClient) {
        this.openWeatherConfiguration = openWeatherConfiguration;
        this.openWeatherService = openWeatherService;
        this.openWeatherController = openWeatherController;
        this.openWeatherClient = openWeatherClient;
    }

    public static void main( String[] args )
    {
        System.out.println( "Welcome to weather prediction application!" );
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}
}
