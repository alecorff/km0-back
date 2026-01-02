package com.kilometre.zero.dto;

import java.time.LocalDate;
import java.util.UUID;

public class TrainingPlanResponse {
	
	private UUID planId;
    private String name;
    private TrainingType type;
    private String goal;
    private String distanceKm;
    private String elevationGain;
    private String location;
    private String fitnessGoal;
    private LocalDate startDate;
    private LocalDate endDate;

    // ===== Getters & Setters =====

    public UUID getPlanId() { return planId; }
    public void setPlanId(UUID planId) { this.planId = planId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public TrainingType getType() { return type; }
    public void setType(TrainingType type) { this.type = type; }

    public String getGoal() { return goal; }
	public void setGoal(String goal) { this.goal = goal; }
	
	public String getDistanceKm() { return distanceKm; }
    public void setDistanceKm(String distanceKm) { this.distanceKm = distanceKm; }

    public String getElevationGain() { return elevationGain; }
    public void setElevationGain(String elevationGain) { this.elevationGain = elevationGain; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

}
