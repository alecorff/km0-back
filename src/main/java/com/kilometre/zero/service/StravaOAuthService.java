package com.kilometre.zero.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kilometre.zero.dto.StravaTokenResponse;


@Service
public class StravaOAuthService {

	@Value("${spring.security.oauth2.client.registration.strava.client-id}")
	private String clientId;

	@Value("${spring.security.oauth2.client.registration.strava.client-secret}")
	private String clientSecret;
	
	private final RestTemplate restTemplate = new RestTemplate();
    private static final String TOKEN_URL = "https://www.strava.com/oauth/token";

	public StravaTokenResponse exchangeCodeForToken(String code) throws JsonProcessingException {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("grant_type", "authorization_code");

        return postForToken(body);
	}
	
	public StravaTokenResponse refreshToken(String refreshToken) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        return postForToken(body);
	}
	
	private StravaTokenResponse postForToken(MultiValueMap<String, String> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<StravaTokenResponse> response =
                restTemplate.postForEntity(TOKEN_URL, request, StravaTokenResponse.class);

        return response.getBody();
    }
}
