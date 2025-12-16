package com.kilometre.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kilometre.zero.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
