package com.strava.dashboard.entities;

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

    private String distance;

    @Column(name = "moving_time")
    private LocalDateTime movingTime;

    @Column(name = "total_elevation_gain")
    private String totalElevationGain;
    
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
    private String averageCadence;
    
    @Column(name = "average_watts")
    private String averageWatts;
    
    @Column(name = "max_watts")
    private String maxWatts;
    
    @Column(name = "weighted_average_watts")
    private String weightedAverageWatts;
    
    private String kilojoules;
    
    @Column(name = "average_heartrate")
    private String averageHeartrate;
    
    @Column(name = "max_heartrate")
    private String maxHeartrate;
    
    @Column(name = "elev_high")
    private String elevHigh;
    
    @Column(name = "elev_low")
    private String elevLow;
    
    private String calories;
    
    private String gear;

    // ======== Getters & Setters ========
    
	public Long getActivityId() { return activityId; }
	public void setActivityId(Long activityId) { this.activityId = activityId; }

	public Long getAthleteId() { return athleteId; }
	public void setAthleteId(Long athleteId) { this.athleteId = athleteId; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public String getDistance() { return distance; }
	public void setDistance(String distance) { this.distance = distance; }

	public LocalDateTime getMovingTime() { return movingTime; }
	public void setMovingTime(LocalDateTime movingTime) { this.movingTime = movingTime; }

	public String getTotalElevationGain() { return totalElevationGain; }
	public void setTotalElevationGain(String totalElevationGain) { this.totalElevationGain = totalElevationGain; }

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

	public String getAverageCadence() { return averageCadence; }
	public void setAverageCadence(String averageCadence) { this.averageCadence = averageCadence; }

	public String getAverageWatts() { return averageWatts; }
	public void setAverageWatts(String averageWatts) { this.averageWatts = averageWatts; }

	public String getMaxWatts() { return maxWatts; }
	public void setMaxWatts(String maxWatts) { this.maxWatts = maxWatts; }

	public String getWeightedAverageWatts() { return weightedAverageWatts; }
	public void setWeightedAverageWatts(String weightedAverageWatts) { this.weightedAverageWatts = weightedAverageWatts; }

	public String getKilojoules() { return kilojoules; }
	public void setKilojoules(String kilojoules) { this.kilojoules = kilojoules; }

	public String getAverageHeartrate() { return averageHeartrate; }
	public void setAverageHeartrate(String averageHeartrate) { this.averageHeartrate = averageHeartrate; }

	public String getMaxHeartrate() { return maxHeartrate; }
	public void setMaxHeartrate(String maxHeartrate) { this.maxHeartrate = maxHeartrate; }

	public String getElevHigh() { return elevHigh; }
	public void setElevHigh(String elevHigh) { this.elevHigh = elevHigh; }

	public String getElevLow() { return elevLow; }
	public void setElevLow(String elevLow) { this.elevLow = elevLow; }

	public String getCalories() { return calories; }
	public void setCalories(String calories) { this.calories = calories; }

	public String getGear() { return gear; }
	public void setGear(String gear) { this.gear = gear; }    
   
}
