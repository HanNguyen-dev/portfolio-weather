package com.portfolio.weather.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeather {
    public Coordinate coord;
    public List<WeatherDescription> weather;
    public String base;
    public Weather main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;
    public Integer dt;
    public Daytime sys;
    public Integer timezone;
    public Integer id;
    public String name;
    public String cod;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Coordinate {
        public Double lon;
        public Double lat;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Weather {
        public Double temp;
        public Double feels_like;
        public Double temp_min;
        public Double temp_max;
        public Integer pressure;
        public Double humidity;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Wind {
        public Double speed;
        public Integer deg;
        public Double gust;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Clouds {
        public Integer all;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Daytime {
        public Integer type;
        public Integer id;
        public Double message;
        public String country;
        public Integer sunrise;
        public Integer sunset;
    }

}
