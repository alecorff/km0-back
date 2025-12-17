package com.kilometre.zero.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilometre.zero.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	List<Activity> findByAthleteIdAndStartDateLocalBetween(
            Long athleteId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
