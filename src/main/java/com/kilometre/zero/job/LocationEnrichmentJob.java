package com.kilometre.zero.job;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kilometre.zero.entities.Activity;
import com.kilometre.zero.location.Location;
import com.kilometre.zero.repository.ActivityRepository;
import com.kilometre.zero.service.LocationService;

@Service
@EnableScheduling
public class LocationEnrichmentJob {

    private static final int BATCH_SIZE = 20;

    private final ActivityRepository activityRepository;
    private final LocationService locationService;

    public LocationEnrichmentJob(
        ActivityRepository activityRepository,
        LocationService locationService
    ) {
        this.activityRepository = activityRepository;
        this.locationService = locationService;
    }

    @Scheduled(fixedDelay = 10_000)
    @Transactional
    public void enrichLocations() {

        List<Activity> activities =
            activityRepository.findActivitiesWithoutLocation(
                PageRequest.of(0, BATCH_SIZE)
            );

        for (Activity activity : activities) {

            locationService
                .resolve(activity.getStartLatitude(), activity.getStartLongitude())
                .filter(Location::isResolved)
                .ifPresent(location -> {
                    activity.setCity(location.city());
                    activity.setCountry(location.country());
                });
        }
    }
}
