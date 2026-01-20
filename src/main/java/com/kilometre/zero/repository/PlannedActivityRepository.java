package com.kilometre.zero.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kilometre.zero.entities.PlannedActivity;

public interface PlannedActivityRepository extends JpaRepository<PlannedActivity, Long> {
	
	List<PlannedActivity> findByPlanIdAndAthleteId(UUID planId, Long athleteId);

	@Modifying
	@Query("""
	    update PlannedActivity pa
	    set pa.status = 'SKIPPED'
	    where pa.planId  = :planId
	      and pa.status = 'PLANNED'
	""")
	void updatePlannedSessionsToSkipped(@Param("planId") UUID planId);

}
