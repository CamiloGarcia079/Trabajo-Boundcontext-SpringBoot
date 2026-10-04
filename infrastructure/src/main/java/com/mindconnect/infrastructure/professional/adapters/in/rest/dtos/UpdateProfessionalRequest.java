package com.mindconnect.infrastructure.professional.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de professionals.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateProfessionalRequest(
        @NotNull UUID documentTypeId,
        @NotBlank @Size(max = 30) String documentNumber,
        @NotBlank @Size(max = 60) String firstName,
        @NotBlank @Size(max = 60) String lastName,
        @NotNull UUID professionalType,
        @NotBlank @Size(max = 100) String licenseNumber,
        @NotNull UUID cityId
) {
}
