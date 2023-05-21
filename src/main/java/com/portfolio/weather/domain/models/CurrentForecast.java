package com.portfolio.weather.domain.models;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentForecast {
    public Double lat;
    public Double lon;
    public String timezone;
    public Integer timezone_offset;
    private CurrentData current;
    public List<Minute> minutely;
    public List<Hour> hourly;
    public List<Day> daily;
    public List<Alert> alerts;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class CurrentData extends WeatherBase {
        public Map<String, Double> rain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Minute {
        public Integer dt;
        public Double precipitation;
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Hour extends WeatherBase {
        public Double wind_gust;
        public Integer pop;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Day {
        public Integer dt;
        public Integer sunrise;
        public Integer sunset;
        public Integer moonrise;
        public Integer moonset;
        public Double moon_phase;
        public Temp temp;
        public FeelsLike feels_like;
        public Integer pressure;
        public Integer humidity;
        public Double dew_point;
        public Double wind_speed;
        public Integer wind_deg;
        public List<WeatherDescription> weather;
        public Integer clouds;
        public Double pop;
        public Double rain;
        public Double uvi;

        @Data
        @SuperBuilder
        @AllArgsConstructor
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = true)
        public static class Temp extends FeelsLike {
            public Double min;
            public Double max;
        }

        @Data
        @SuperBuilder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class FeelsLike {
            public Double day;
            public Double night;
            public Double eve;
            public Double morn;
        }
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherBase {
        public Integer dt;
        public Integer sunrise;
        public Integer sunset;
        public Double temp;
        public Double feels_like;
        public Integer pressure;
        public Integer humidity;
        public Double dew_point;
        public Double uvi;
        public Integer clouds;
        public Integer visibility;
        public Double wind_speed;
        public Integer wind_deg;
        public List<WeatherDescription> weather;
        public Map<String, Double> rain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Alert {
        public String sender_name;
        public String event;
        public Integer start;
        public Integer end;
        public String description;
        public List<String> tags;
    }

}
