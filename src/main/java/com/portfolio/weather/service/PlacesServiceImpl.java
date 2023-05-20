package com.portfolio.weather.service;

import com.portfolio.weather.component.GooglePlacesApiComponent;
import com.portfolio.weather.domain.interfaces.IPlacesService;
import com.portfolio.weather.domain.models.PlacesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacesServiceImpl implements IPlacesService {

    @Autowired
    private GooglePlacesApiComponent googlePlacesApiComponent;

    public PlacesResponse getPlaceAutoComplete(String query) {
        return googlePlacesApiComponent.getPlaceAutoComplete(query);
    }

    public PlacesResponse getPlaceAutoComplete(String query, String session) {
        return googlePlacesApiComponent.getPlaceAutoComplete(query, session);
    }
}
