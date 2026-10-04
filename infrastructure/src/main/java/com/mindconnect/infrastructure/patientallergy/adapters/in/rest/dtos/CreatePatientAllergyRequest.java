package com.mindconnect.infrastructure.patientallergy.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de patient_allergies.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreatePatientAllergyRequest(
        @NotNull UUID patientId,
        @NotBlank @Size(max = 200) String substance,
        String reaction,
        @NotBlank @Size(max = 20) String severity,
        @NotNull LocalDateTime recordedAt,
        @NotNull UUID recordedBy
) {
}
