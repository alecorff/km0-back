package com.kilometre.zero.job;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kilometre.zero.entities.User;
import com.kilometre.zero.service.ActivityService;
import com.kilometre.zero.service.UserService;

@Service
@EnableScheduling
public class StravaSyncJob {
	
	private static Log logger = LogFactory.getLog(StravaSyncJob.class);

    private final UserService userService;
    private final ActivityService activityService;

    public StravaSyncJob(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }

    @Scheduled(fixedDelayString = "${strava.sync.interval}")
    @Transactional
    public void syncActivities() {

        // Récupération des utilisateurs à synchroniser
        List<User> users = userService.getAllUsers();
        
        // Synchro user par user
        for (User user : users) {
            try {
                String accessToken = userService.getValidStravaAccessToken(user.getAthleteId());
                LocalDateTime lastSync = user.getLastSync();
                boolean updated;

                if (lastSync == null) {
                    activityService.syncAllActivities(accessToken, user.getAthleteId());
                    updated = true;
                } else {
                	updated = activityService.syncLastActivities(accessToken, user.getAthleteId(), lastSync);
                }

                // Mettre à jour le timestamp de la dernière synchronisation
                LocalDateTime now = LocalDateTime.now();
                if (updated) {
                    userService.updateLastSync(user.getAthleteId(), now);
                }

            } catch (Exception e) {
                // On log l'erreur mais on continue avec les autres users
            	logger.error("Erreur lors de la synchronisation de l'utilisateur " + user.getAthleteId() + ": " + e.getMessage());
            }
        }
    }
}