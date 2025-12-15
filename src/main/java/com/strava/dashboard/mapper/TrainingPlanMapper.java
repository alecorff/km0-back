package com.strava.dashboard.mapper;

import com.strava.dashboard.dto.TrainingPlanRequest;
import com.strava.dashboard.dto.TrainingPlanResponse;
import com.strava.dashboard.entities.TrainingPlan;

public class TrainingPlanMapper {

//    public static TrainingPlan toEntity(
//            TrainingPlanRequest dto,
//            Long athleteId
//    ) {
//        TrainingPlan plan = new TrainingPlan();
//
//        plan.setAthleteId(athleteId);
//        plan.setName(dto.getName());
//        plan.setType(dto.getType());
//        plan.setLocation(dto.getLocation());
//        plan.setFitnessGoal(dto.getFitnessGoal());
//        plan.setDistanceKm(dto.getDistanceKm());
//        plan.setElevationGain(dto.getElevationGain());
//        plan.setStartDate(dto.getStartDate());
//        plan.setEndDate(dto.getEndDate());
//
//        return plan;
//    }
	
	private TrainingPlanMapper() {

    }

    // ===== REQUEST -> ENTITY =====
    public static TrainingPlan toEntity(
            TrainingPlanRequest dto,
            Long athleteId
    ) {
        TrainingPlan plan = new TrainingPlan();

        plan.setAthleteId(athleteId);
        plan.setName(dto.getName());
        plan.setType(dto.getType());
        plan.setLocation(dto.getLocation());
        plan.setFitnessGoal(dto.getFitnessGoal());
        plan.setDistanceKm(dto.getDistanceKm());
        plan.setElevationGain(dto.getElevationGain());
        plan.setStartDate(dto.getStartDate());
        plan.setEndDate(dto.getEndDate());

        return plan;
    }

    // ===== ENTITY -> RESPONSE =====
    public static TrainingPlanResponse toResponse(TrainingPlan plan) {
        TrainingPlanResponse response = new TrainingPlanResponse();

        response.setPlanId(plan.getPlanId());
        response.setName(plan.getName());
        response.setType(plan.getType());
        response.setDistanceKm(plan.getDistanceKm());
        response.setElevationGain(plan.getElevationGain());
        response.setLocation(plan.getLocation());
        response.setFitnessGoal(plan.getFitnessGoal());
        response.setStartDate(plan.getStartDate());
        response.setEndDate(plan.getEndDate());

        return response;
    }
}
