package com.portfolio.weather.controller;

import com.portfolio.weather.domain.interfaces.IWeatherService;
import com.portfolio.weather.domain.models.ForecastsResponse;

// import com.weather.webservice.error.CityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/weather")
@CrossOrigin("${app.cors}")
public class WeatherController {

    private final IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // @GetMapping()
    // public ResponseEntity<CityForecasts> getForecasts(@RequestParam(value = "city", required = true) String city) {
    //     // CityForecasts cityForecasts = weatherService.getTheWeather(city);
    //     // if (cityForecasts == null || cityForecasts.location.city == null) {
    //     //     throw new CityNotFoundException();
    //     // }
    //     // ResponseEntity<CityForecasts> responseCityForecasts = new ResponseEntity<>(cityForecasts, HttpStatus.OK);

    //     // return responseCityForecasts;
    //     return new ResponseEntity<>(CityForecasts.Builder().build(), HttpStatus.OK);
    // }

    @GetMapping()
    public ResponseEntity<ForecastsResponse> getForecasts(@RequestParam(required = false) Double lat,
                                                          @RequestParam(required = false) Double lon,
                                                          HttpServletRequest request) {
        ForecastsResponse response;
        if (lat == null && lon == null) {
            response = weatherService.getForecastsByIp(request.getRemoteAddr());
        } else {
            response = weatherService.getForecasts(lat, lon);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{placeId}")
    public ResponseEntity<ForecastsResponse> getForecastsByPlaceId(@PathVariable String placeId) {
        var response = weatherService.getForecasts(placeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{placeId}/{session}")
    public ResponseEntity<ForecastsResponse> getForecastsByPlaceId(@PathVariable String placeId,
                                                                   @PathVariable String session) {
        ForecastsResponse response = weatherService.getForecasts(placeId, session);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
