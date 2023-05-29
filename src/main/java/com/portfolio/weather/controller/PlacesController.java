package com.portfolio.weather.controller;

import com.portfolio.weather.domain.interfaces.IPlacesService;
import com.portfolio.weather.domain.models.PlaceResponse;
import com.portfolio.weather.domain.models.PlacesResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/places")
@CrossOrigin("${app.cors}")
public class PlacesController {

    IPlacesService placesService;

    public PlacesController(IPlacesService placesService) {
        this.placesService = placesService;
    }

    @GetMapping()
    public PlacesResponse getAutoCompletedCities(@RequestParam String q,
                                                 @RequestParam(value = "session", required = false) String session) {
        if (session == null || session.isEmpty()) {
            return placesService.getPlaceAutoComplete(q);
        } else {
            return placesService.getPlaceAutoComplete(q, session);
        }
    }

    @GetMapping("/{placeId}/{session}")
    public PlaceResponse getPlaceDetails(@PathVariable("placeId") String placeId,
                                         @PathVariable(value = "session", required = false) String session) {
        if (session == null || session.isEmpty()) {
            return placesService.getPlaceDetails(placeId);
        } else {
            return placesService.getPlaceDetails(placeId, session);
        }
    }

}
