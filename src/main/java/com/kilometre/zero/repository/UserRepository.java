package com.kilometre.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kilometre.zero.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAthleteId(Long athleteId);
}
