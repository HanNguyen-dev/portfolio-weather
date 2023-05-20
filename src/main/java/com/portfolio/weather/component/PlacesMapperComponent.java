package com.portfolio.weather.component;

import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import com.portfolio.weather.domain.models.PlaceResponse;
import com.portfolio.weather.domain.models.PlacesResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PlacesMapperComponent {

    public PlaceResponse convertTo(PlaceDetails placeDetails) {
        return PlaceResponse.builder()
                .placeId(placeDetails.placeId)
                .name(placeDetails.name)
                .url(placeDetails.url)
                .geoLocation(PlaceResponse.GeoLocation.builder()
                        .latitude(placeDetails.geometry.location.lat)
                        .longitude(placeDetails.geometry.location.lng)
                        .build())
                .build();
    }

    public PlacesResponse convertTo(AutocompletePrediction[] predictions, PlaceAutocompleteRequest.SessionToken token) {
        return PlacesResponse.builder()
                .session(token.toUrlValue())
                .size(predictions.length)
                .places(Arrays.stream(predictions)
                        .map(this::convertTo)
                        .toList())
                .build();
    }

    private PlacesResponse.Place convertTo(AutocompletePrediction prediction) {
        return PlacesResponse.Place.builder()
                .placeId(prediction.placeId)
                .description(prediction.description)
                .build();
    }

}
