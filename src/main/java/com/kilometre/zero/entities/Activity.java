package com.kilometre.zero.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {
	
	@Id
	@Column(name = "activity_id")
    private Long activityId;
	
    @Column(name = "athlete_id")
    private Long athleteId;

    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    private Double distance;

    @Column(name = "moving_time")
    private Integer movingTime;

    @Column(name = "total_elevation_gain")
    private Double totalElevationGain;
    
    @Column(name = "sport_type")
    private String sportType;
    
    @Column(name = "start_date_local")
    private LocalDateTime startDateLocal;
    
    @Column(columnDefinition = "TEXT")
    private String polyline;
    
    @Column(name = "average_speed")
    private String averageSpeed;
    
    @Column(name = "max_speed")
    private String maxSpeed;
    
    @Column(name = "average_cadence")
    private Double averageCadence;
    
    @Column(name = "average_watts")
    private Double averageWatts;
    
    @Column(name = "max_watts")
    private Double maxWatts;
    
    @Column(name = "weighted_average_watts")
    private Double weightedAverageWatts;
    
    private Double kilojoules;
    
    @Column(name = "average_heartrate")
    private Double averageHeartrate;
    
    @Column(name = "max_heartrate")
    private Double maxHeartrate;
    
    @Column(name = "elev_high")
    private Double elevHigh;
    
    @Column(name = "elev_low")
    private Double elevLow;
    
    private Double calories;
    
    private String gear;
    
    @Column(name = "session_type")
    private String sessionType;

    // ======== Getters & Setters ========
    
	public Long getActivityId() { return activityId; }
	public void setActivityId(Long activityId) { this.activityId = activityId; }

	public Long getAthleteId() { return athleteId; }
	public void setAthleteId(Long athleteId) { this.athleteId = athleteId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public Double getDistance() { return distance; }
	public void setDistance(Double distance) { this.distance = distance; }

	public Integer getMovingTime() { return movingTime; }
	public void setMovingTime(Integer movingTime) { this.movingTime = movingTime; }

	public Double getTotalElevationGain() { return totalElevationGain; }
	public void setTotalElevationGain(Double totalElevationGain) { this.totalElevationGain = totalElevationGain; }

	public String getSportType() { return sportType; }
	public void setSportType(String sportType) { this.sportType = sportType; }

	public LocalDateTime getStartDateLocal() { return startDateLocal; }
	public void setStartDateLocal(LocalDateTime startDateLocal) { this.startDateLocal = startDateLocal; }

	public String getPolyline() { return polyline; }
	public void setPolyline(String polyline) { this.polyline = polyline; }

	public String getAverageSpeed() { return averageSpeed; }
	public void setAverageSpeed(String averageSpeed) { this.averageSpeed = averageSpeed; }

	public String getMaxSpeed() { return maxSpeed; }
	public void setMaxSpeed(String maxSpeed) { this.maxSpeed = maxSpeed; }

	public Double getAverageCadence() { return averageCadence; }
	public void setAverageCadence(Double averageCadence) { this.averageCadence = averageCadence; }

	public Double getAverageWatts() { return averageWatts; }
	public void setAverageWatts(Double averageWatts) { this.averageWatts = averageWatts; }

	public Double getMaxWatts() { return maxWatts; }
	public void setMaxWatts(Double maxWatts) { this.maxWatts = maxWatts; }

	public Double getWeightedAverageWatts() { return weightedAverageWatts; }
	public void setWeightedAverageWatts(Double weightedAverageWatts) { this.weightedAverageWatts = weightedAverageWatts; }

	public Double getKilojoules() { return kilojoules; }
	public void setKilojoules(Double kilojoules) { this.kilojoules = kilojoules; }

	public Double getAverageHeartrate() { return averageHeartrate; }
	public void setAverageHeartrate(Double averageHeartrate) { this.averageHeartrate = averageHeartrate; }

	public Double getMaxHeartrate() { return maxHeartrate; }
	public void setMaxHeartrate(Double maxHeartrate) { this.maxHeartrate = maxHeartrate; }

	public Double getElevHigh() { return elevHigh; }
	public void setElevHigh(Double elevHigh) { this.elevHigh = elevHigh; }

	public Double getElevLow() { return elevLow; }
	public void setElevLow(Double elevLow) { this.elevLow = elevLow; }

	public Double getCalories() { return calories; }
	public void setCalories(Double calories) { this.calories = calories; }

	public String getGear() { return gear; }
	public void setGear(String gear) { this.gear = gear; }
	
	public String getSessionType() { return sessionType; }
	public void setSessionType(String sessionType) { this.sessionType = sessionType; } 
}
