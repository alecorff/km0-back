package com.kilometre.zero.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.kilometre.zero.dto.StravaActivity;
import com.kilometre.zero.entities.Activity;

@Service
public class ActivityMapper {

	public Activity toEntity(StravaActivity dto, Long athleteId) {
		Activity activity = new Activity();

		activity.setActivityId(dto.getId());
		activity.setAthleteId(athleteId);

		activity.setName(dto.getName());
		activity.setDescription(dto.getDescription());
		activity.setDistance(metersToKm(dto.getDistance()));

		activity.setMovingTime(dto.getMovingTime());

		activity.setTotalElevationGain(toDouble(dto.getTotalElevationGain()));
		activity.setSportType(dto.getSportType());

		activity.setStartDateLocal(parseDate(dto.getStartDateLocal()));

		if (dto.getMap() != null) {
			activity.setPolyline(dto.getMap().getSummary_polyline());
		}

		activity.setAverageSpeed(speedToPace(dto.getAverageSpeed()));
		activity.setMaxSpeed(speedToPace(dto.getMaxSpeed()));

		activity.setAverageCadence(toDouble(dto.getAverageCadence()));
		activity.setAverageWatts(toDouble(dto.getAverageWatts()));
		activity.setMaxWatts(toDouble(dto.getMaxWatts()));
		activity.setWeightedAverageWatts(toDouble(dto.getWeightedAverageWatts()));
		activity.setKilojoules(toDouble(dto.getKilojoules()));

		activity.setAverageHeartrate(toDouble(dto.getAverageHeartrate()));
		activity.setMaxHeartrate(toDouble(dto.getMaxHeartrate()));
		activity.setElevHigh(toDouble(dto.getElevHigh()));
		activity.setElevLow(toDouble(dto.getElevLow()));

		activity.setCalories(toDouble(dto.getCalories()));

		if (dto.getGear() != null) {
			activity.setGear(dto.getGear().getName());
		}

		return activity;
	}

	/* ===== Helpers ===== */
	/**
	 * Convertit m/s → mm'ss"/km
	 */
	private String speedToPace(Double speedMetersPerSecond) {
		if (speedMetersPerSecond == null || speedMetersPerSecond <= 0) {
			return null;
		}

		// secondes par km
		double secondsPerKm = 1000.0 / speedMetersPerSecond;

		int minutes = (int) (secondsPerKm / 60);
		int seconds = (int) Math.round(secondsPerKm % 60);

		// gestion des arrondis type 7'60"
		if (seconds == 60) {
			minutes++;
			seconds = 0;
		}

		return String.format("%d'%02d\"/km", minutes, seconds);
	}

	private LocalDateTime parseDate(String isoDate) {
		return isoDate != null ? LocalDateTime.parse(isoDate.replace("Z", "")) : null;
	}

	private Double metersToKm(Double meters) {
		if (meters == null)
			return null;
		return round(meters / 1000.0, 2);
	}

	private Double round(double value, int scale) {
		return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

	private Double toDouble(Number value) {
		return value != null ? value.doubleValue() : null;
	}
}
