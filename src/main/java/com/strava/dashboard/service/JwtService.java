package com.strava.dashboard.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder encoder;
    private final long expirationHours;

    public JwtService(
            JwtEncoder encoder,
            @Value("${app.jwt.expiration-hours:720}") long expirationHours 
    ) {
        this.encoder = encoder;
        this.expirationHours = expirationHours;
    }

    public String generateToken(Map<String, Object> claims) {
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
            .issuedAt(now)
            .expiresAt(now.plus(expirationHours, ChronoUnit.HOURS))
            .claims(existingClaims -> existingClaims.putAll(claims))
            .build();
        
        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        
        return encoder.encode(JwtEncoderParameters.from(jwsHeader, claimsSet)).getTokenValue();
    }
}

