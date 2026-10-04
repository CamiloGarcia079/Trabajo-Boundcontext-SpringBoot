package com.mindconnect.infrastructure.riskassessment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de risk_assessments.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateRiskAssessmentRequest(
        @NotNull UUID encounterId,
        @NotNull UUID riskLevelId,
        @NotNull Boolean suicidalIdeation,
        @NotNull Boolean suicidePlan,
        @NotNull Boolean suicideIntent,
        @NotNull Boolean selfHarm,
        @NotNull Boolean harmToOthers,
        @NotBlank String riskFactors,
        @NotBlank String protectiveFactors,
        @NotBlank String clinicalActions,
        @NotBlank String observations,
        @NotNull LocalDateTime assessedAt,
        @NotNull UUID assessedBy
) {
}
