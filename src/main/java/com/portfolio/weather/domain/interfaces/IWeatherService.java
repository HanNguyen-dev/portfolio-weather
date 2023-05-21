package com.portfolio.weather.domain.interfaces;

import com.portfolio.weather.domain.models.ForecastsResponse;

public interface IWeatherService {

  public ForecastsResponse getForecasts(Double latitude, Double longitude);

}
