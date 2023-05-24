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
        CurrentForecast forecasts = openWeatherComponent.getForecast(latitude, longitude);
        return mapper.convertTo(forecasts);
    }

    public ForecastsResponse getForecasts(String placeId) {
        PlaceResponse place = googlePlacesApiComponent.getPlaceDetails(placeId);
        return getForecasts(place.getGeoLocation().getLatitude(), place.getGeoLocation().getLongitude());
    }

    public ForecastsResponse getForecastsByIp(String ipAddress) {
        Location location = ipInfoComponent.getLocation(ipAddress);
        return getForecasts(location.getLatitude(), location.getLongitude());
    }
}
