package com.mindconnect.infrastructure.encounter.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de encounters.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateEncounterRequest(
        @NotNull UUID clinicalRecordId,
        @NotNull UUID professionalId,
        @NotNull UUID encounterTypeId,
        @NotNull LocalDateTime startedAt,
        @NotNull LocalDateTime endedAt,
        @NotBlank String reasonForVisit,
        @NotBlank String currentCondition,
        @NotNull UUID modalityId,
        @NotNull UUID statusId,
        @NotNull UUID updatedBy
) {
}
