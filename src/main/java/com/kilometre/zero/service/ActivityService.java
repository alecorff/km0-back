package com.kilometre.zero.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kilometre.zero.dto.StravaActivity;
import com.kilometre.zero.entities.Activity;
import com.kilometre.zero.mapper.ActivityMapper;
import com.kilometre.zero.repository.ActivityRepository;

@Service
public class ActivityService {
	
	private static final String STRAVA_ACTIVITIES_URL =
            "https://www.strava.com/api/v3/athlete/activities";

	private static final Set<String> RUNNING_TYPES = Set.of(
		    "Run", "TrailRun"
		);
    
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    
    public ActivityService(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    public void syncAllActivities(String accessToken, Long athleteId) {

        int page = 1;
        int perPage = 200;

        while (true) {

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(STRAVA_ACTIVITIES_URL)
                    .queryParam("page", page)
                    .queryParam("per_page", perPage);

            ResponseEntity<StravaActivity[]> response =
                    new RestTemplate().exchange(
                            uriBuilder.toUriString(),
                            HttpMethod.GET,
                            entity,
                            StravaActivity[].class
                    );

            StravaActivity[] activities = response.getBody();

            if (activities == null || activities.length == 0) {
                break;
            }

            for (StravaActivity dto : activities) {
            	// On vérifie que l'activité est bien Run ou TrailRun
            	if (!isRunning(dto)) {
                    continue;
                }
            	
                Activity activity = activityMapper.toEntity(dto, athleteId);
                activityRepository.save(activity);
            }

            page++;
        }
    }
    
    public void syncLastActivities(String accessToken, Long athleteId, LocalDateTime lastSync) {

        int page = 1;
        int perPage = 200;
        
        int after = convert(lastSync);

        while (true) {

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(STRAVA_ACTIVITIES_URL)
                    .queryParam("after", after)
                    .queryParam("page", page)
                    .queryParam("per_page", perPage);

            ResponseEntity<StravaActivity[]> response =
                    new RestTemplate().exchange(
                            uriBuilder.toUriString(),
                            HttpMethod.GET,
                            entity,
                            StravaActivity[].class
                    );

            StravaActivity[] activities = response.getBody();

            if (activities == null || activities.length == 0) {
                break;
            }

            for (StravaActivity dto : activities) {
            	// On vérifie que l'activité est bien Run ou TrailRun
            	if (!isRunning(dto)) {
                    continue;
                }

                Activity activity = activityMapper.toEntity(dto, athleteId);
                activityRepository.save(activity);
            }

            page++;
        }
    }
    
    private int convert(LocalDateTime lastSync) {
    	ZoneId zoneId = ZoneId.of("Europe/Paris");
    	Instant instant = lastSync.atZone(zoneId).toInstant();

    	return (int) instant.getEpochSecond();
	}

	private boolean isRunning(StravaActivity dto) {
    	return RUNNING_TYPES.contains(dto.getSportType());
    }
	
	public List<Activity> getActivitiesForPlanPeriod(Long athleteId, LocalDateTime planStartDate) {
        LocalDateTime now = LocalDateTime.now();
        return activityRepository.findByAthleteIdAndStartDateLocalBetween(athleteId, planStartDate, now);
    }


}
