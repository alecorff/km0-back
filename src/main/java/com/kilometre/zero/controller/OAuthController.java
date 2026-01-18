package com.kilometre.zero.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kilometre.zero.dto.StravaTokenResponse;
import com.kilometre.zero.entities.User;
import com.kilometre.zero.service.JwtService;
import com.kilometre.zero.service.StravaOAuthService;
import com.kilometre.zero.service.UserService;

@RestController
public class OAuthController {
	
	@Value("${app.frontend.base-url}")
	private String frontendBaseUrl;

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
                "%s/loginSuccess?jwt=%s&firstname=%s&lastname=%s&avatar=%s",
                frontendBaseUrl,
                jwt,
                user.getFirstname(),
                user.getLastname(),
                user.getAvatar()
            );
            
        return new RedirectView(redirectUrl);
    }
}
