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

	public StravaTokenResponse exchangeCodeForToken(String code) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("client_id", clientId);
		body.add("client_secret", clientSecret);
		body.add("code", code);
		body.add("grant_type", "authorization_code");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		ResponseEntity<StravaTokenResponse> response = restTemplate.postForEntity(
				"https://www.strava.com/oauth/token",
				request, 
				StravaTokenResponse.class);

		return response.getBody();
	}
}
