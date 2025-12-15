package com.strava.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strava.dashboard.entities.TrainingPlan;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
	
	List<TrainingPlan> findAllByAthleteId(Long athleteId);

}
