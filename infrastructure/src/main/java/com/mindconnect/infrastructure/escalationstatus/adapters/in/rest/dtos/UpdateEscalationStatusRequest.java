package com.mindconnect.infrastructure.escalationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de escalations_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateEscalationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
