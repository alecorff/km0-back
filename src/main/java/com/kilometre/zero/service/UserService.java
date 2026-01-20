package com.kilometre.zero.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kilometre.zero.dto.StravaTokenResponse;
import com.kilometre.zero.entities.User;
import com.kilometre.zero.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StravaOAuthService stravaOAuthService;

    public UserService(UserRepository userRepository, StravaOAuthService stravaOAuthService) {
        this.userRepository = userRepository;
        this.stravaOAuthService = stravaOAuthService;
    }

    public User saveOrUpdateFromStrava(StravaTokenResponse token) {

        User user = userRepository.findByAthleteId(token.athlete.id);

        if (user == null) {
            user = new User();
            user.setAthleteId(token.athlete.id);
            user.setLastSync(null);
        }

        user.setFirstname(token.athlete.firstname);
        user.setLastname(token.athlete.lastname);
        user.setAvatar(token.athlete.profile);

        user.setAccessToken(token.accessToken);
        user.setRefreshToken(token.refreshToken);
        user.setTokenExpiresAt(
                Instant.ofEpochSecond(token.expiresAt)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
        );

        userRepository.save(user);
        return user;
    }
    
    public LocalDateTime getLastSyncByAthleteId(Long athleteId) {
        User user = userRepository.findByAthleteId(athleteId);
        return (user != null && user.getLastSync() != null)
                ? user.getLastSync()
                : null;
    }
        
    public void updateLastSync(Long athleteId, LocalDateTime timestamp) {
        User user = userRepository.findByAthleteId(athleteId);
        if (user != null) {
            user.setLastSync(timestamp);
            userRepository.save(user);
        }
    }
    
    public boolean existsByAthleteId(Long athleteId) {
    	User user = userRepository.findByAthleteId(athleteId);
    	return user != null;
    }
    
    public String getValidStravaAccessToken(Long athleteId) {
        User user = userRepository.findByAthleteId(athleteId);
        
        // Conversion de LocalDateTime en epoch seconds
        long tokenExpiresAtEpoch = user.getTokenExpiresAt().atZone(ZoneId.systemDefault()).toEpochSecond();
        long nowEpoch = Instant.now().getEpochSecond();

        long buffer = 2 * 60; // 2 minutes

        if (tokenExpiresAtEpoch - nowEpoch < buffer) {
            // Token expiré ou presque → refresh
            StravaTokenResponse refreshed = stravaOAuthService.refreshToken(user.getRefreshToken());

            user.setAccessToken(refreshed.accessToken);
            user.setRefreshToken(refreshed.refreshToken);
            
            LocalDateTime expiresAt = Instant.ofEpochSecond(refreshed.expiresAt).atZone(ZoneId.systemDefault()).toLocalDateTime();
            user.setTokenExpiresAt(expiresAt);

            userRepository.save(user);
        }

        return user.getAccessToken();
    }
    
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }
}
