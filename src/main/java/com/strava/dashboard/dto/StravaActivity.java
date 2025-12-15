package com.strava.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StravaActivity {
	
	private Long id;
    private String name;
    private String description;

    private Double distance;

    @JsonProperty("moving_time")
    private Integer movingTime;

    @JsonProperty("total_elevation_gain")
    private Double totalElevationGain;

    @JsonProperty("sport_type")
    private String sportType;

    @JsonProperty("start_date_local")
    private String startDateLocal;

    private Map map;

    @JsonProperty("average_speed")
    private Double averageSpeed;

    @JsonProperty("max_speed")
    private Double maxSpeed;

    @JsonProperty("average_cadence")
    private Double averageCadence;

    @JsonProperty("average_watts")
    private Double averageWatts;

    @JsonProperty("max_watts")
    private Double maxWatts;

    @JsonProperty("weighted_average_watts")
    private Double weightedAverageWatts;

    private Double kilojoules;

    @JsonProperty("average_heartrate")
    private Double averageHeartrate;

    @JsonProperty("max_heartrate")
    private Double maxHeartrate;

    @JsonProperty("elev_high")
    private Double elevHigh;

    @JsonProperty("elev_low")
    private Double elevLow;

    private Double calories;

    private Gear gear;
    
    // ======== Getters & Setters ========

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getMovingTime() {
		return movingTime;
	}

	public void setMovingTime(Integer movingTime) {
		this.movingTime = movingTime;
	}

	public Double getTotalElevationGain() {
		return totalElevationGain;
	}

	public void setTotalElevationGain(Double totalElevationGain) {
		this.totalElevationGain = totalElevationGain;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getStartDateLocal() {
		return startDateLocal;
	}

	public void setStartDateLocal(String startDateLocal) {
		this.startDateLocal = startDateLocal;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Double getAverageCadence() {
		return averageCadence;
	}

	public void setAverageCadence(Double averageCadence) {
		this.averageCadence = averageCadence;
	}

	public Double getAverageWatts() {
		return averageWatts;
	}

	public void setAverageWatts(Double averageWatts) {
		this.averageWatts = averageWatts;
	}

	public Double getMaxWatts() {
		return maxWatts;
	}

	public void setMaxWatts(Double maxWatts) {
		this.maxWatts = maxWatts;
	}

	public Double getWeightedAverageWatts() {
		return weightedAverageWatts;
	}

	public void setWeightedAverageWatts(Double weightedAverageWatts) {
		this.weightedAverageWatts = weightedAverageWatts;
	}

	public Double getKilojoules() {
		return kilojoules;
	}

	public void setKilojoules(Double kilojoules) {
		this.kilojoules = kilojoules;
	}

	public Double getAverageHeartrate() {
		return averageHeartrate;
	}

	public void setAverageHeartrate(Double averageHeartrate) {
		this.averageHeartrate = averageHeartrate;
	}

	public Double getMaxHeartrate() {
		return maxHeartrate;
	}

	public void setMaxHeartrate(Double maxHeartrate) {
		this.maxHeartrate = maxHeartrate;
	}

	public Double getElevHigh() {
		return elevHigh;
	}

	public void setElevHigh(Double elevHigh) {
		this.elevHigh = elevHigh;
	}

	public Double getElevLow() {
		return elevLow;
	}

	public void setElevLow(Double elevLow) {
		this.elevLow = elevLow;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Gear getGear() {
		return gear;
	}

	public void setGear(Gear gear) {
		this.gear = gear;
	}

}
