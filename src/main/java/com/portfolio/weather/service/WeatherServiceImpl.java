package com.portfolio.weather.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.portfolio.weather.component.WeatherMapperComponent;
import com.portfolio.weather.component.OpenWeatherComponent;
import com.portfolio.weather.domain.interfaces.IWeatherService;
import com.portfolio.weather.domain.models.ForecastsResponse;
import com.portfolio.weather.domain.models.CurrentForecast;

@Service
public class WeatherServiceImpl implements IWeatherService {

    @Autowired
    private OpenWeatherComponent openWeatherComponent;

    @Autowired
    private WeatherMapperComponent mapper;

    public ForecastsResponse getForecasts(Double latitude, Double longitude) {
        CurrentForecast forecasts = openWeatherComponent.getForecast(latitude, longitude);

        return mapper.convertTo(forecasts);
    }

}
