package com.strava.dashboard.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.strava.dashboard.dto.StravaTokenResponse;
import com.strava.dashboard.entities.User;
import com.strava.dashboard.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    
    public String getAccessTokenByAthleteId(Long athleteId) {
        User user = userRepository.findByAthleteId(athleteId);
        return (user != null && user.getAccessToken() != null)
                ? user.getAccessToken().toString()
                : null;
    }
    
    public void updateLastSync(Long athleteId, LocalDateTime timestamp) {
        User user = userRepository.findByAthleteId(athleteId);
        if (user != null) {
            user.setLastSync(timestamp);
            userRepository.save(user);
        }
    }
}
