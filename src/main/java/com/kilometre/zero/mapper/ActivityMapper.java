package com.kilometre.zero.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
        activity.setDistance(toString(dto.getDistance()));

        activity.setMovingTime(secondsToLocalDateTime(dto.getMovingTime()));

        activity.setTotalElevationGain(toString(dto.getTotalElevationGain()));
        activity.setSportType(dto.getSportType());

        activity.setStartDateLocal(parseDate(dto.getStartDateLocal()));

        if (dto.getMap() != null) {
            activity.setPolyline(dto.getMap().getSummary_polyline());
        }

        activity.setAverageSpeed(toString(dto.getAverageSpeed()));
        activity.setMaxSpeed(toString(dto.getMaxSpeed()));
        activity.setAverageCadence(toString(dto.getAverageCadence()));
        activity.setAverageWatts(toString(dto.getAverageWatts()));
        activity.setMaxWatts(toString(dto.getMaxWatts()));
        activity.setWeightedAverageWatts(toString(dto.getWeightedAverageWatts()));
        activity.setKilojoules(toString(dto.getKilojoules()));

        activity.setAverageHeartrate(toString(dto.getAverageHeartrate()));
        activity.setMaxHeartrate(toString(dto.getMaxHeartrate()));
        activity.setElevHigh(toString(dto.getElevHigh()));
        activity.setElevLow(toString(dto.getElevLow()));

        activity.setCalories(toString(dto.getCalories()));

        if (dto.getGear() != null) {
            activity.setGear(dto.getGear().getName());
        }

        return activity;
    }

    /* ===== Helpers ===== */
    private String toString(Number value) {
        return value != null ? value.toString() : null;
    }

    private LocalDateTime parseDate(String isoDate) {
        return isoDate != null
                ? LocalDateTime.parse(isoDate.replace("Z", ""))
                : null;
    }

    private LocalDateTime secondsToLocalDateTime(Integer seconds) {
        return seconds != null
                ? LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.UTC)
                : null;
    }
}

