package com.rahul.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "open-weather")
public class OpenWeatherConfiguration {
    private String apiKey;
    private String url;
}
