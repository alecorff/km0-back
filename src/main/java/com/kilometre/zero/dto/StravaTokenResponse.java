package com.kilometre.zero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StravaTokenResponse {
	
	@JsonProperty("token_type")
    public String tokenType;

    @JsonProperty("expires_at")
    public long expiresAt;

    @JsonProperty("expires_in")
    public int expiresIn;

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("access_token")
    public String accessToken;

    public StravaAthlete athlete;

}
