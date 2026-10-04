package com.mindconnect.infrastructure.priority.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de priorities.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdatePriorityRequest(
        @NotBlank @Size(max = 50) String namePriority
) {
}
