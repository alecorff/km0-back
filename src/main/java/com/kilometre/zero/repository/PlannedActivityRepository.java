package com.kilometre.zero.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kilometre.zero.entities.PlannedActivity;

public interface PlannedActivityRepository extends JpaRepository<PlannedActivity, Long> {
	
	List<PlannedActivity> findByPlanIdAndAthleteId(UUID planId, Long athleteId);

}
