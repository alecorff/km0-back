package com.kilometre.zero.controller;

import java.time.LocalDateTime;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kilometre.zero.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtDecoder jwtDecoder;

    public UserController(UserService userService, JwtDecoder jwtDecoder) {
        this.userService = userService;
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/lastSync")
    public LocalDateTime getLastSync(@RequestHeader("Authorization") String authorizationHeader) {
    	
    	String jwt = authorizationHeader.replace("Bearer ", "");
    	Jwt decodedJwt = jwtDecoder.decode(jwt);
        Long athleteId = decodedJwt.getClaim("athleteId");
        
        LocalDateTime lastSync = userService.getLastSyncByAthleteId(athleteId);

        return lastSync != null ? lastSync : null;
    }
    
}
