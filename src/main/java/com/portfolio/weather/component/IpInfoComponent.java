package com.portfolio.weather.component;

import com.portfolio.weather.domain.models.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IpInfoComponent {

    @Value("${app.apis.ipinfo.api-key}")
    private String apiKey;

    @Value("${app.apis.ipinfo.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public IpInfoComponent() {
        restTemplate = new RestTemplate();
    }

    public Location getLocation(String ipAddress) {
        String url = String.format("%s/%s?token=%s", baseUrl, ipAddress, apiKey);
        Location location = restTemplate.getForEntity(url, Location.class).getBody();
        return parseGeoLocation(location);
    }

    private Location parseGeoLocation(Location location) {
        String[] latitudeLongitude = location.getLoc().split(",");
        location.setLatitude(Double.parseDouble(latitudeLongitude[0]));
        location.setLongitude(Double.parseDouble(latitudeLongitude[1]));
        return location;
    }

}
