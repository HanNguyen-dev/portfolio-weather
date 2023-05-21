package com.portfolio.weather.component;

import com.portfolio.weather.domain.models.CurrentForecast;
import com.portfolio.weather.domain.models.CurrentWeather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherComponent {
    final RestTemplate restTemplate;

    @Value("${app.apiKey}")
    private String apiKey;
    @Value("${app.baseUrl}")
    private String baseUrl;

    public OpenWeatherComponent() {
        this.restTemplate = new RestTemplate();
    }

    public CurrentWeather getCurrentWeather(String city) {
        String url = baseUrl + "weather?q=" + city + "&appid=" + apiKey + "&units=imperial";
        CurrentWeather currentWeather = restTemplate.getForEntity(url, CurrentWeather.class).getBody();
        return currentWeather;
    }

    public CurrentForecast getForecast(Double latitude, Double longitude) {
        String url = baseUrl + "onecall?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey + "&units=imperial";
        CurrentForecast currentForecast = restTemplate.getForEntity(url, CurrentForecast.class).getBody();
        return currentForecast;
    }
}
