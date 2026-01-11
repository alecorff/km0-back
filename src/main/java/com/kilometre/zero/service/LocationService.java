package com.kilometre.zero.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kilometre.zero.dto.NominatimResponse;
import com.kilometre.zero.location.Location;

@Service
public class LocationService {

    private static final String NOMINATIM_URL =
        "https://nominatim.openstreetmap.org/reverse" +
        "?format=json&addressdetails=1&lat={lat}&lon={lon}";

    private final RestTemplate restTemplate;

    private final Map<String, Location> cache = new ConcurrentHashMap<>();

    public LocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Location> resolve(double lat, double lng) {

        String key = cacheKey(lat, lng);

        Location cached = cache.get(key);
        if (cached != null) {
            return Optional.of(cached);
        }

        try {
            Location location = fetchFromNominatim(lat, lng);
            cache.put(key, location);
            return Optional.of(location);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Location fetchFromNominatim(double lat, double lng) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "KilometreZero/1.0 (contact@kilometrezero.com)");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<NominatimResponse> response =
            restTemplate.exchange(
                NOMINATIM_URL,
                HttpMethod.GET,
                entity,
                NominatimResponse.class,
                lat,
                lng
            );

        var address = response.getBody().getAddress();

        String city =
            Optional.ofNullable(address.getCity())
                .or(() -> Optional.ofNullable(address.getTown()))
                .or(() -> Optional.ofNullable(address.getVillage()))
                .orElse(null);

        String country =
            Optional.ofNullable(address.getCountryCode())
                .map(String::toUpperCase)
                .orElse(null);

        return new Location(city, country);
    }

    private String cacheKey(double lat, double lng) {
        return String.format("%.2f,%.2f", lat, lng);
    }
}