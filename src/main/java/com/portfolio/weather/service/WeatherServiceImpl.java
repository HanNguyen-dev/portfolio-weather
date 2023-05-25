package com.portfolio.weather.service;

import com.portfolio.weather.component.GooglePlacesApiComponent;
import com.portfolio.weather.component.IpInfoComponent;
import com.portfolio.weather.domain.models.Location;
import com.portfolio.weather.domain.models.PlaceResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.portfolio.weather.component.WeatherMapperComponent;
import com.portfolio.weather.component.OpenWeatherComponent;
import com.portfolio.weather.domain.interfaces.IWeatherService;
import com.portfolio.weather.domain.models.ForecastsResponse;
import com.portfolio.weather.domain.models.ForecastsResponse.ForecastsResponseBuilder;
import com.portfolio.weather.domain.models.CurrentForecast;

@Service
public class WeatherServiceImpl implements IWeatherService {

    @Autowired
    private OpenWeatherComponent openWeatherComponent;

    @Autowired
    private GooglePlacesApiComponent googlePlacesApiComponent;

    @Autowired
    private IpInfoComponent ipInfoComponent;

    @Autowired
    private WeatherMapperComponent mapper;

    public ForecastsResponse getForecasts(Double latitude, Double longitude) {
        return requestForecasts(latitude, longitude).build();
    }

    public ForecastsResponse getForecasts(String placeId) {
        PlaceResponse place = googlePlacesApiComponent.getPlaceDetails(placeId);
        var forecastsBuilder = requestForecasts(place.getGeoLocation().getLatitude(), place.getGeoLocation().getLongitude());
        return mapper.addLocation(forecastsBuilder, place).build();
    }

    public ForecastsResponse getForecasts(String placeId, String session) {
        PlaceResponse place = googlePlacesApiComponent.getPlaceDetails(placeId, session);
        var forecastsBuilder = requestForecasts(place.getGeoLocation().getLatitude(), place.getGeoLocation().getLongitude());
        return mapper.addLocation(forecastsBuilder, place).build();
    }

    public ForecastsResponse getForecastsByIp(String ipAddress) {
        Location location = ipInfoComponent.getLocation(ipAddress);
        var forecastsBuilder = requestForecasts(location.getLatitude(), location.getLongitude());
        return mapper.addLocation(forecastsBuilder, location).build();
    }

    private ForecastsResponseBuilder requestForecasts(Double latitude, Double longitude) {
        CurrentForecast forecasts = openWeatherComponent.getForecast(latitude, longitude);
        return mapper.getForecastsBuilder(forecasts);
    }
}
