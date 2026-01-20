package com.kilometre.zero.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilometre.zero.entities.TrainingPlan;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
	
	List<TrainingPlan> findAllByAthleteId(Long athleteId);
	
	Optional<TrainingPlan> findByPlanIdAndAthleteId(UUID planId, Long athleteId);
	
	List<TrainingPlan> findByEndDate(LocalDate endDate);

}
