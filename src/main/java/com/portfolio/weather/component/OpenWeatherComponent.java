package com.portfolio.weather.component;

import com.portfolio.weather.domain.models.CurrentForecast;
import com.portfolio.weather.domain.models.CurrentWeather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherComponent {

    final RestTemplate restTemplate;

    @Value("${app.apis.openweather.api-key}")
    private String apiKey;

    @Value("${app.apis.openweather.base-url}")
    private String baseUrl;

    public OpenWeatherComponent() {
        this.restTemplate = new RestTemplate();
    }

    public CurrentWeather getCurrentWeather(String city) {
        String url = String.format("%s/weather?q=%s&appid=%s&units=imperial", baseUrl, city, apiKey);
        CurrentWeather currentWeather = restTemplate.getForEntity(url, CurrentWeather.class).getBody();
        return currentWeather;
    }

    public CurrentForecast getForecast(Double latitude, Double longitude) {
        String url = String.format("%s/onecall?units=imperial&lat=%s&lon=%s&appid=%s" , baseUrl, latitude, longitude, apiKey);
        CurrentForecast currentForecast = restTemplate.getForEntity(url, CurrentForecast.class).getBody();
        return currentForecast;
    }
}
