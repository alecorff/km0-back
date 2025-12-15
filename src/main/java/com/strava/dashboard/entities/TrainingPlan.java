package com.strava.dashboard.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.strava.dashboard.dto.TrainingType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plans")
public class TrainingPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "plan_id")
    private UUID planId;
	
	@Column(name = "athlete_id")
    private Long athleteId;

    private String name;

    @Enumerated(EnumType.STRING)
    private TrainingType type;
    
    @Column(name = "distance_km")
    private String distanceKm;
    
    @Column(name = "elevation_gain")
    private String elevationGain;

    private String location;

    @Column(name = "fitness_goal")
    private String fitnessGoal;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    // ======== Getters & Setters ========

	public UUID getPlanId() { return planId; }
	public void setPlanId(UUID planId) { this.planId = planId; }

	public Long getAthleteId() { return athleteId; }
	public void setAthleteId(Long athleteId) { this.athleteId = athleteId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public TrainingType getType() { return type; }
	public void setType(TrainingType type) { this.type = type; }

	public String getDistanceKm() { return distanceKm; }
	public void setDistanceKm(String distanceKm) { this.distanceKm = distanceKm; }

	public String getElevationGain() { return elevationGain; }
	public void setElevationGain(String elevationGain) { this.elevationGain = elevationGain; }

	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }

	public String getFitnessGoal() { return fitnessGoal; }
	public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

	public LocalDateTime getStartDate() { return startDate; }
	public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

	public LocalDateTime getEndDate() { return endDate; }
	public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }    

}
