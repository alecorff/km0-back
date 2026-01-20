package com.kilometre.zero.job;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kilometre.zero.entities.TrainingPlan;
import com.kilometre.zero.repository.PlannedActivityRepository;
import com.kilometre.zero.repository.TrainingPlanRepository;

@Service
@EnableScheduling
public class TrainingPlanCompletionJob {

    private final TrainingPlanRepository trainingPlanRepository;
    private final PlannedActivityRepository plannedActivityRepository;

    public TrainingPlanCompletionJob(TrainingPlanRepository trainingPlanRepository, PlannedActivityRepository plannedActivityRepository
    ) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.plannedActivityRepository = plannedActivityRepository;
    }

    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void closeFinishedTrainingPlans() {

        LocalDate yesterday = LocalDate.now().minusDays(1);

        List<TrainingPlan> finishedPlans = trainingPlanRepository.findByEndDate(yesterday);

        for (TrainingPlan plan : finishedPlans) {
        	plannedActivityRepository.updatePlannedSessionsToSkipped(plan.getPlanId());
        }
    }
}

