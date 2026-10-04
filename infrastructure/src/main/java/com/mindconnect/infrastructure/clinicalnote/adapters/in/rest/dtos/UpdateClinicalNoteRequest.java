package com.mindconnect.infrastructure.clinicalnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de clinical_notes.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateClinicalNoteRequest(
        @NotNull UUID encounterId,
        @NotNull UUID professionalId,
        @NotBlank String subjective,
        @NotBlank String objective,
        @NotBlank String assessment,
        @NotBlank String plan,
        @NotBlank String additionalNotes,
        @NotNull LocalDateTime signedAt
) {
}
