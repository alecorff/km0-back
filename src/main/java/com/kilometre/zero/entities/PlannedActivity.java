package com.kilometre.zero.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planned_activities")
public class PlannedActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planned_activity_id")
    private Long plannedActivityId;

    @Column(name = "plan_id", nullable = false)
    private UUID planId;

    @Column(name = "athlete_id", nullable = false)
    private Long athleteId;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @Column(columnDefinition = "TEXT")
    private String name;
    
    @Type(JsonBinaryType.class)
    @Column(name = "steps_json", columnDefinition = "jsonb")
    private Object stepsJson;

    @Column(name = "planned_duration_min")
    private Integer plannedDurationMin;

    @Column(name = "planned_distance_km")
    private Double plannedDistanceKm;

    @Column(name = "session_type")
    private String sessionType; 

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "status")
    private String status;
    
    // ======== Getters & Setters ========

	public Long getPlannedActivityId() { return plannedActivityId; }
	public void setPlannedActivityId(Long plannedActivityId) { this.plannedActivityId = plannedActivityId; }

	public UUID getPlanId() { return planId; }
	public void setPlanId(UUID planId) { this.planId = planId; }

	public Long getAthleteId() { return athleteId; }
	public void setAthleteId(Long athleteId) { this.athleteId = athleteId; }

	public LocalDate getScheduledDate() { return scheduledDate; }
	public void setScheduledDate(LocalDate scheduledDate) { this.scheduledDate = scheduledDate; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Object getStepsJson() { return stepsJson; }
	public void setStepsJson(Object stepsJson) { this.stepsJson = stepsJson; }
	
	public Integer getPlannedDurationMin() { return plannedDurationMin; }
	public void setPlannedDurationMin(Integer plannedDurationMin) { this.plannedDurationMin = plannedDurationMin; }

	public Double getPlannedDistanceKm() { return plannedDistanceKm; }
	public void setPlannedDistanceKm(Double plannedDistanceKm) { this.plannedDistanceKm = plannedDistanceKm; }

	public String getSessionType() { return sessionType; }
	public void setSessionType(String sessionType) { this.sessionType = sessionType; }

	public Long getActivityId() { return activityId; }
	public void setActivityId(Long activityId) { this.activityId = activityId; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }    
}
