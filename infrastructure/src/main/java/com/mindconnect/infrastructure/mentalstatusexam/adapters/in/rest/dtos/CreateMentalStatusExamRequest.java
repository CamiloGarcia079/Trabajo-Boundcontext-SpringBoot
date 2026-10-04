package com.mindconnect.infrastructure.mentalstatusexam.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de mental_status_exams.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateMentalStatusExamRequest(
        @NotNull UUID encounterId,
        @NotBlank String appearance,
        @NotBlank String behavior,
        @NotBlank String attitude,
        @NotBlank String consciousness,
        @NotBlank String orientation,
        @NotBlank String attention,
        @NotBlank String memory,
        @NotBlank String speech,
        @NotBlank String mood,
        @NotBlank String affect,
        @NotBlank String thoughtProcess,
        @NotBlank String thoughtContent,
        @NotBlank String perception,
        @NotBlank String judgment,
        @NotBlank String insight,
        @NotBlank String psychomotorActivity,
        @NotBlank String observations,
        @NotNull UUID createdBy
) {
}
