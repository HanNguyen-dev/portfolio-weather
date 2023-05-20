package com.portfolio.weather.controller;

import com.portfolio.weather.domain.interfaces.IPlacesService;
import com.portfolio.weather.domain.models.PlacesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class PlacesController {

    IPlacesService placesService;

    public PlacesController(IPlacesService placesService) {
        this.placesService = placesService;
    }
    
    @GetMapping()
    public PlacesResponse getAutoCompletedCities(@RequestParam String q,
                                                 @RequestParam(value = "session", required = false) String session) {
        if (session == null || session.equals("")) {
            return placesService.getPlaceAutoComplete(q);
        } else {
            return placesService.getPlaceAutoComplete(q, session);
        }
    }

}
