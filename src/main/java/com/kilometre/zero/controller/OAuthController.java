package com.kilometre.zero.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private final JwtDecoder jwtDecoder;

    public OAuthController(StravaOAuthService stravaOAuthService, UserService userService, JwtService jwtService, JwtDecoder jwtDecoder) {
        this.stravaOAuthService = stravaOAuthService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/exchange_token")
    public RedirectView handleAuthorizationCode(@RequestParam(required = false) String code, @RequestParam(required = false) String error) throws JsonProcessingException {
    	// Si l'utilisateur a annulé chez Strava
        if (error != null) {
            return new RedirectView(frontendBaseUrl);
        }

        // Sécurité supplémentaire
        if (code == null) {
            return new RedirectView(frontendBaseUrl);
        }
    	
    	
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
    
    @GetMapping("/api/auth/hasValidSession")
    public ResponseEntity<Boolean> hasValidSession(@RequestHeader("Authorization") String authorizationHeader) {
        
    	if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(false);
        }
    	
    	String jwt = authorizationHeader.replace("Bearer ", "");
		Jwt decodedJwt = jwtDecoder.decode(jwt);
		Long athleteId = decodedJwt.getClaim("athleteId");
        boolean exists = userService.existsByAthleteId(athleteId);
        if (!exists) return ResponseEntity.ok(false);

        // Rafraîchir le token Strava si nécessaire
        userService.getValidStravaAccessToken(athleteId);

        return ResponseEntity.ok(true);
    }
}
