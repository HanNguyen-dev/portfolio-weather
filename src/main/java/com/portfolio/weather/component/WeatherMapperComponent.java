package com.portfolio.weather.component;

import java.util.List;

import com.portfolio.weather.domain.models.CurrentForecast;
import com.portfolio.weather.domain.models.ForecastsResponse;
import com.portfolio.weather.domain.models.ForecastsResponse.CurrentObservation.Wind;
import com.portfolio.weather.domain.models.ForecastsResponse.CurrentObservation.Condition;
import com.portfolio.weather.domain.models.ForecastsResponse.CurrentObservation.Astronomy;
import com.portfolio.weather.domain.models.ForecastsResponse.CurrentObservation.Atmosphere;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapperComponent {

    @Value("${app.apis.openweather.icon-base-url}")
    private String iconBaseUrl;

    public ForecastsResponse convertTo(CurrentForecast forecasts) {
        CurrentForecast.CurrentData currentWeather = forecasts.getCurrent();


        Wind wind = Wind.builder()
            .direction(currentWeather.getWind_deg())
            .speed(currentWeather.getWind_speed())
            .build();

        Atmosphere atmosphere = Atmosphere.builder()
            .humidity(currentWeather.getHumidity())
            .visibility(currentWeather.getVisibility().doubleValue())
            .pressure(currentWeather.getPressure().doubleValue())
            .build();

        Astronomy astronomy = Astronomy.builder()
            .sunrise(currentWeather.getSunrise().toString())
            .sunset(currentWeather.getSunset().toString())
            .build();

        Condition condition = Condition.builder()
            .text(currentWeather.getWeather().get(0).getDescription())
            .temperature(currentWeather.getTemp().intValue())
            .iconUrl(String.format("%s/%s@4x.png", iconBaseUrl, currentWeather.getWeather().get(0).getIcon()))
            .build();

        ForecastsResponse.CurrentObservation currentObservation = ForecastsResponse.CurrentObservation.builder()
            .wind(wind)
            .atmosphere(atmosphere)
            .astronomy(astronomy)
            .condition(condition)
            .build();

        List<ForecastsResponse.Forecast> resultForecasts = forecasts.daily.stream()
            .map(day -> {
                return ForecastsResponse.Forecast.builder()
                    .day(day.getDt().toString())
                    .low(day.getTemp().getMin().intValue())
                    .high(day.getTemp().getMax().intValue())
                    .text(day.getWeather().get(0).getDescription())
                    .iconUrl(String.format("%s/%s@4x.png", iconBaseUrl, day.getWeather().get(0).getIcon()))
                    .build();
            }).toList();


        ForecastsResponse forecastsResponse = ForecastsResponse.builder()
                           .current_observation(currentObservation)
                           .forecasts(resultForecasts)
                           .build();

        return forecastsResponse;
    }
}
