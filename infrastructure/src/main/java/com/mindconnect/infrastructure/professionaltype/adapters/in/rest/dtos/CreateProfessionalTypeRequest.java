package com.mindconnect.infrastructure.professionaltype.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de professional_types.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateProfessionalTypeRequest(
        @NotBlank @Size(max = 40) String name
) {
}
