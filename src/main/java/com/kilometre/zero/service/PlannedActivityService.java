package com.kilometre.zero.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kilometre.zero.dto.PlannedActivityDto;
import com.kilometre.zero.entities.PlannedActivity;
import com.kilometre.zero.repository.PlannedActivityRepository;

@Service
public class PlannedActivityService {

	private final PlannedActivityRepository repository;

	public PlannedActivityService(PlannedActivityRepository repository) {
		this.repository = repository;
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

}
