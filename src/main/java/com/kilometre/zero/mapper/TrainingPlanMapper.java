package com.kilometre.zero.mapper;

import com.kilometre.zero.dto.TrainingPlanRequest;
import com.kilometre.zero.dto.TrainingPlanResponse;
import com.kilometre.zero.entities.TrainingPlan;

public class TrainingPlanMapper {
	
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
        plan.setGoal(dto.getGoal());
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
        response.setGoal(plan.getGoal());
        response.setDistanceKm(plan.getDistanceKm());
        response.setElevationGain(plan.getElevationGain());
        response.setLocation(plan.getLocation());
        response.setFitnessGoal(plan.getFitnessGoal());
        response.setStartDate(plan.getStartDate());
        response.setEndDate(plan.getEndDate());

        return response;
    }
}
