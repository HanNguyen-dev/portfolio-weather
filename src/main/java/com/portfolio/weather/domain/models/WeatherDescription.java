package com.portfolio.weather.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDescription {
    public Integer id;
    public String main;
    public String description;
    public String icon;
}