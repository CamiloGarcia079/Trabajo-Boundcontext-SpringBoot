package com.mindconnect.application.treatmentgoal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mindconnect.domain.treatmentgoal.model.aggregate.TreatmentGoal;

public record TreatmentGoalResponse(
        UUID id,
        UUID treatmentPlanId,
        String description,
        LocalDate targetDate,
        LocalDateTime completedAt,
        String notes,
        UUID treatmentGoalId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static TreatmentGoalResponse fromDomain(TreatmentGoal aggregate) {
        return new TreatmentGoalResponse(
                aggregate.id().value(),
                aggregate.treatmentPlanId(), aggregate.description(), aggregate.targetDate(), aggregate.completedAt(), aggregate.notes(), aggregate.treatmentGoalId(), aggregate.createdAt(), aggregate.updatedAt());
    }
}
