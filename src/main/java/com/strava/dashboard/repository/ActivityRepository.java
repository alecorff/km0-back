package com.strava.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strava.dashboard.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
