package com.portfolio.weather.component;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlaceAutocompleteRequest.SessionToken;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceDetails;
import com.portfolio.weather.domain.models.PlaceResponse;
import com.portfolio.weather.domain.models.PlacesResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
public class GooglePlacesApiComponent {

    @Value("${app.apis.google.api-key}")
    private String apiKey;

    @Autowired
    private PlacesMapperComponent placesMapper;

    private GeoApiContext context;

    public PlacesResponse getPlaceAutoComplete(String query, String session) {
        SessionToken token = new SessionToken(UUID.fromString(session));
        return getPlaceAutoComplete(query, token);
    }

    public PlacesResponse getPlaceAutoComplete(String query) {
        SessionToken token = new SessionToken();
        return getPlaceAutoComplete(query, token);
    }

    private PlacesResponse getPlaceAutoComplete(String query, SessionToken token) {
        PlaceAutocompleteRequest request = PlacesApi.placeAutocomplete(getContext(), query, token);

        AutocompletePrediction[] results = request.awaitIgnoreError();

        return placesMapper.convertTo(results, token);
    }

    public PlaceResponse getPlaceDetails(String placeId) {
        SessionToken token = new SessionToken();
        return getPlaceDetails(placeId, token);
    }

    public PlaceResponse getPlaceDetails(String placeId, String session) {
        SessionToken token = new SessionToken(session);
        return getPlaceDetails(placeId, token);
    }

    private PlaceResponse getPlaceDetails(String placeId, SessionToken token) {
        PlaceDetailsRequest request = PlacesApi.placeDetails(getContext(), placeId, token);

        PlaceDetails result = request.awaitIgnoreError();
        return placesMapper.convertTo(result);
    }

    private GeoApiContext getContext() {
        if (context == null) {
            context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
        }
        return context;
    }

}
