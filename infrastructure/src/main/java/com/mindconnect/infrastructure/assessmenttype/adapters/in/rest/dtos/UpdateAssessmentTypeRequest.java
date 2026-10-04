package com.mindconnect.infrastructure.assessmenttype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de assessment_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateAssessmentTypeRequest(
        @NotBlank @Size(max = 20) String code,
        @NotBlank @Size(max = 50) String name,
        @NotBlank String description
) {
}
