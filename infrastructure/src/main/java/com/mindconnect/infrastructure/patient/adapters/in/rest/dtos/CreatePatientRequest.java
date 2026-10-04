package com.mindconnect.infrastructure.patient.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de patients.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreatePatientRequest(
        @NotNull UUID documentTypeId,
        @NotBlank @Size(max = 30) String documentNumber,
        @NotBlank @Size(max = 50) String firstName,
        @Size(max = 50) String middleName,
        @NotBlank @Size(max = 50) String lastName,
        @Size(max = 50) String secondLastName,
        @NotNull LocalDate birthDate,
        @NotNull UUID biologicalSexId,
        @NotNull UUID genderIdentity,
        @NotBlank @Size(max = 150) String email,
        @NotBlank @Size(max = 30) String phone,
        @NotBlank @Size(max = 250) String address,
        UUID createdBy,
        UUID updatedBy,
        @NotNull UUID cityId
) {
}
