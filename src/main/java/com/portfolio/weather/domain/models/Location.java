package com.portfolio.weather.domain.models;

import lombok.Data;

@Data
public class Location {
    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private String postal;
    private String timezone;
    private Double latitude;
    private Double longitude;
}
