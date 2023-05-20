package com.portfolio.weather.component;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.portfolio.weather.domain.models.PlacesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GooglePlacesApiComponent {

    @Value("${app.apis.google.api-key}")
    private String apiKey;

    private GeoApiContext context;

    public PlacesResponse getPlaceAutoComplete(String query, String session) {
        SessionToken token = new SessionToken(session);
        return getPlaceAutoComplete(query, token);
    }

    public PlacesResponse getPlaceAutoComplete(String query) {
        SessionToken token = new SessionToken();
        return getPlaceAutoComplete(query, token);
    }

    private PlacesResponse getPlaceAutoComplete(String query, SessionToken token) {
        PlaceAutocompleteRequest request = PlacesApi.placeAutocomplete(getContext(), query, token);

        AutocompletePrediction[] results = request.awaitIgnoreError();
        return convertTo(results, token);
    }

    private GeoApiContext getContext() {
        if (context == null) {
            context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
        }
        return context;
    }

    private PlacesResponse convertTo(AutocompletePrediction[] predictions, SessionToken token) {
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
