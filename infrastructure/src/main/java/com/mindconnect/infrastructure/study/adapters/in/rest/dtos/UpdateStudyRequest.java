package com.mindconnect.infrastructure.study.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de studies.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateStudyRequest(
        @NotBlank @Size(max = 40) String name
) {
}
