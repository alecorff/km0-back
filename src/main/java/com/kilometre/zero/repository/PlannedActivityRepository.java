package com.kilometre.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kilometre.zero.entities.PlannedActivity;

public interface PlannedActivityRepository extends JpaRepository<PlannedActivity, Long> {

}
