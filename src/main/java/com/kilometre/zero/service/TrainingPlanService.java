package com.kilometre.zero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kilometre.zero.dto.TrainingPlanRequest;
import com.kilometre.zero.dto.TrainingPlanResponse;
import com.kilometre.zero.entities.TrainingPlan;
import com.kilometre.zero.mapper.TrainingPlanMapper;
import com.kilometre.zero.repository.TrainingPlanRepository;

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
