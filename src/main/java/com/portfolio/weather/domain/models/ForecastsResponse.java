package com.portfolio.weather.domain.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecastsResponse {
    private Location location;
    private CurrentObservation current_observation;
    private List<Forecast> forecasts;

    @Data
    @Builder
    public static class Location {
        public String city;
        public String region;
        public Integer woeid;
        public String country;
        public Double lat;
        public Double Long;
        public String timezone_id;
    }

    @Data
    @Builder
    public static class CurrentObservation {

        private Wind wind;
        private Atmosphere atmosphere;
        private Astronomy astronomy;
        private Condition condition;

        @Data
        @Builder
        public static class Wind {
            private Integer chill;
            private Integer direction;
            private Double speed;
        }

        @Data
        @Builder
        public static class Atmosphere {
            private Integer humidity;
            private Double visibility;
            private Double pressure;
            private Integer rising;
        }

        @Data
        @Builder
        public static class Astronomy {
            private String sunrise;
            private String sunset;
        }

        @Data
        @Builder
        public static class Condition {
            private String text;
            private Integer code;
            private Integer temperature;
            private String iconUrl;
        }
    }

    @Data
    @Builder
    public static class Forecast {
        private String day;
        private Integer date;
        private Integer low;
        private Integer high;
        private String text;
        private Integer code;
        private String iconUrl;
    }

}
