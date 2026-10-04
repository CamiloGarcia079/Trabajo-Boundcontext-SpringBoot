package com.mindconnect.infrastructure.professionalstudy.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de professional_studies.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateProfessionalStudyRequest(
        @NotNull UUID studyId,
        @NotNull UUID professionalId,
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 100) String university,
        @NotNull Boolean isValid,
        @Size(max = 60) String resolutionNumber,
        @NotNull UUID countryId
) {
}
