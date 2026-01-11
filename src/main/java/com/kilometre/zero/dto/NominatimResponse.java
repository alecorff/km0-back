package com.kilometre.zero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NominatimResponse {
    private Address address;

    @Data
    public static class Address {
        private String city;
        private String town;
        private String village;
        
        @JsonProperty("country_code")
        private String countryCode;
    }
}
