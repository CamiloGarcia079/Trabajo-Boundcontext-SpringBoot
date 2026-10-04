package com.mindconnect.infrastructure.clinicalrecord.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de clinical_records.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateClinicalRecordRequest(
        @NotNull UUID patientId,
        @NotNull LocalDateTime creationDate,
        @NotBlank @Size(max = 50) String recordNumber,
        @NotNull LocalDateTime openedAt,
        @NotNull LocalDateTime closedAt,
        @NotNull UUID statusId
) {
}
