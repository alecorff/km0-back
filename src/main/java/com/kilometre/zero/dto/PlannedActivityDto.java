package com.kilometre.zero.dto;

import java.time.LocalDate;
import java.util.UUID;

public class PlannedActivityDto {
	
	private UUID planId;
    private Long athleteId;
    private LocalDate scheduledDate;
    private String name;
    private Integer plannedDurationMin;
    private Double plannedDistanceKm;
    private Integer plannedElevationGain;
    private String sessionType;
    private String stepsJson;
    private Long activityId;
    private String status;
    
    // ======== Getters & Setters ========
    
	public UUID getPlanId() {
		return planId;
	}
	public void setPlanId(UUID planId) {
		this.planId = planId;
	}
	public Long getAthleteId() {
		return athleteId;
	}
	public void setAthleteId(Long athleteId) {
		this.athleteId = athleteId;
	}
	public LocalDate getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPlannedDurationMin() {
		return plannedDurationMin;
	}
	public void setPlannedDurationMin(Integer plannedDurationMin) {
		this.plannedDurationMin = plannedDurationMin;
	}
	public Double getPlannedDistanceKm() {
		return plannedDistanceKm;
	}
	public void setPlannedDistanceKm(Double plannedDistanceKm) {
		this.plannedDistanceKm = plannedDistanceKm;
	}
	public Integer getPlannedElevationGain() {
		return plannedElevationGain;
	}
	public void setPlannedElevationGain(Integer plannedElevationGain) {
		this.plannedElevationGain = plannedElevationGain;
	}
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	public String getStepsJson() {
		return stepsJson;
	}
	public void setStepsJson(String stepsJson) {
		this.stepsJson = stepsJson;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
