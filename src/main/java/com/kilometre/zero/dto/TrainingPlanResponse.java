package com.kilometre.zero.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingPlanResponse {
	
	private UUID planId;
    private String name;
    private TrainingType type;
    private String distanceKm;
    private String elevationGain;
    private String location;
    private String fitnessGoal;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // ===== Getters & Setters =====

    public UUID getPlanId() { return planId; }
    public void setPlanId(UUID planId) { this.planId = planId; }

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
