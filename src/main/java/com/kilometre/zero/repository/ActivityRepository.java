package com.kilometre.zero.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kilometre.zero.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	List<Activity> findByAthleteId(Long athleteId);

	List<Activity> findByAthleteIdAndStartDateLocalBetween(Long athleteId, LocalDateTime startDate,
			LocalDateTime endDate);

	@Query("""
			    select a from Activity a
			    where a.city is null
			      and a.startLatitude is not null
			      and a.startLongitude is not null
			""")
	List<Activity> findActivitiesWithoutLocation(Pageable pageable);

}
