package com.strava.dashboard.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.strava.dashboard.dto.StravaTokenResponse;
import com.strava.dashboard.entities.User;
import com.strava.dashboard.service.JwtService;
import com.strava.dashboard.service.StravaOAuthService;
import com.strava.dashboard.service.UserService;

@RestController
public class OAuthController {

    private final StravaOAuthService stravaOAuthService;
    private final UserService userService;
    private final JwtService jwtService;

    public OAuthController(StravaOAuthService stravaOAuthService, UserService userService, JwtService jwtService) {
        this.stravaOAuthService = stravaOAuthService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/exchange_token")
    public RedirectView handleAuthorizationCode(@RequestParam String code) throws JsonProcessingException {
    	StravaTokenResponse tokenResponse = stravaOAuthService.exchangeCodeForToken(code);
    	
    	// Stockage en base
        User user = userService.saveOrUpdateFromStrava(tokenResponse);
        
        String jwt = jwtService.generateToken(Map.of("athleteId", user.getAthleteId()));
        
        String redirectUrl = String.format(
                "http://localhost:4200/loginSuccess?jwt=%s&firstname=%s&lastname=%s&avatar=%s",
                jwt,
                user.getFirstname(),
                user.getLastname(),
                user.getAvatar()
            );
            
        return new RedirectView(redirectUrl);
    }
}
