package com.portfolio.weather.domain.interfaces;

import com.portfolio.weather.domain.models.PlaceResponse;
import com.portfolio.weather.domain.models.PlacesResponse;

public interface IPlacesService {
    public PlacesResponse getPlaceAutoComplete(String query);

    public PlacesResponse getPlaceAutoComplete(String query, String session);

    public PlaceResponse getPlaceDetails(String placeId);

    public PlaceResponse getPlaceDetails(String placeId, String session);

}
