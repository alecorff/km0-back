package com.strava.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.strava.dashboard.dto.TrainingPlanRequest;
import com.strava.dashboard.dto.TrainingPlanResponse;
import com.strava.dashboard.entities.TrainingPlan;
import com.strava.dashboard.mapper.TrainingPlanMapper;
import com.strava.dashboard.repository.TrainingPlanRepository;

@Service
public class TrainingPlanService {

    private final TrainingPlanRepository repository;

    public TrainingPlanService(TrainingPlanRepository repository) {
        this.repository = repository;
    }

    public TrainingPlanResponse createPlan(TrainingPlanRequest request, Long athleteId) {
        TrainingPlan plan = TrainingPlanMapper.toEntity(request, athleteId);
        TrainingPlan savedPlan = repository.save(plan);

        return TrainingPlanMapper.toResponse(savedPlan);
    }
    
    public List<TrainingPlanResponse> getAllPlans(Long athleteId) {

        List<TrainingPlan> plans = repository.findAllByAthleteId(athleteId);

        return plans.stream().map(TrainingPlanMapper::toResponse).toList();
    }

}
