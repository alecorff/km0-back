package com.kilometre.zero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilometre.zero.entities.TrainingPlan;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
	
	List<TrainingPlan> findAllByAthleteId(Long athleteId);

}
