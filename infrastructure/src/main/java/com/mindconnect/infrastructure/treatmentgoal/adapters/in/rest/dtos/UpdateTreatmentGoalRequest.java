package com.mindconnect.infrastructure.treatmentgoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de treatment_goals.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateTreatmentGoalRequest(
        @NotNull UUID treatmentPlanId,
        @NotBlank String description,
        @NotNull LocalDate targetDate,
        @NotNull LocalDateTime completedAt,
        @NotBlank String notes,
        @NotNull UUID treatmentGoalId
) {
}
