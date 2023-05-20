package com.portfolio.weather.domain.models;

import lombok.Builder;
import lombok.Data;

import java.net.URL;

@Data
@Builder
public class PlaceResponse {
    private String placeId;
    private String name;
    private URL url;
    private GeoLocation geoLocation;

    @Data
    @Builder
    public static class GeoLocation {
        private double latitude;
        private double longitude;
    }
}
