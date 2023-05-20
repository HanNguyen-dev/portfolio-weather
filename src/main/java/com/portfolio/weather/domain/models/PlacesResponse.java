package com.portfolio.weather.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlacesResponse {
    private String session;
    private Integer size;
    private Iterable<Place> places;

    @Data
    @Builder
    public static class Place {
        private String placeId;
        private String description;
    }
}
