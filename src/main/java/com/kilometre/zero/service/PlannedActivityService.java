package com.kilometre.zero.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kilometre.zero.dto.LinkPlannedActivityDto;
import com.kilometre.zero.dto.PlannedActivityDto;
import com.kilometre.zero.entities.PlannedActivity;
import com.kilometre.zero.repository.PlannedActivityRepository;

@Service
public class PlannedActivityService {

	private final PlannedActivityRepository repository;
	private final ActivityService activityService;

	public PlannedActivityService(PlannedActivityRepository repository, ActivityService activityService) {
		this.repository = repository;
		this.activityService = activityService;
	}

	public ResponseEntity<PlannedActivity> createPlannedActivity(PlannedActivityDto request, Long athleteId) {

		PlannedActivity activity;
		if (request.getActivityId() != null) {
			activity = repository.findById(request.getActivityId())
					.orElseThrow(() -> new RuntimeException("PlannedActivity not found"));
		} else {
			activity = new PlannedActivity();
		}

		activity.setPlanId(request.getPlanId());
		activity.setAthleteId(athleteId);
		activity.setScheduledDate(request.getScheduledDate());
		activity.setName(request.getName());
		activity.setPlannedDistanceKm(request.getPlannedDistanceKm());
		activity.setPlannedDurationMin(request.getPlannedDurationMin());
		activity.setSessionType(request.getSessionType());
		activity.setStepsJson(request.getStepsJson());
		activity.setStatus(request.getStatus());

		PlannedActivity saved = repository.save(activity);
		return ResponseEntity.ok(saved);
	}

	public List<PlannedActivity> getPlannedActivitiesForPlan(UUID planId, Long athleteId) {
		return repository.findByPlanIdAndAthleteId(planId, athleteId);
	}

	public PlannedActivity updatePlannedActivity(Long id, PlannedActivityDto request, Long athleteId) {

		PlannedActivity activity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("planned_activity_not_found"));

		if (!activity.getAthleteId().equals(athleteId)) {
			throw new RuntimeException("forbidden_planned_activity");
		}

		if ("DONE".equals(activity.getStatus())) {
			throw new RuntimeException("bad_status_activity");
		}

		activity.setScheduledDate(request.getScheduledDate());
		activity.setName(request.getName());
		activity.setSessionType(request.getSessionType());
		activity.setPlannedDistanceKm(request.getPlannedDistanceKm());
		activity.setPlannedDurationMin(request.getPlannedDurationMin());
		activity.setStepsJson(request.getStepsJson());

		return repository.save(activity);
	}
	
	public void linkPlannedActivity(LinkPlannedActivityDto dto, Long activityId, Long athleteId) {
        // Récupérer la séance planifiée
        PlannedActivity planned = repository.findById(dto.getPlannedActivityId())
                .orElseThrow(() -> new RuntimeException("planned_activity_not_found"));

        // Mettre à jour la séance planifiée
        planned.setActivityId(activityId);
        planned.setStatus("DONE");
        repository.save(planned);

        // Mettre à jour la séance réelle
        activityService.updateActivityToLink(activityId, dto, athleteId);
    }
}
