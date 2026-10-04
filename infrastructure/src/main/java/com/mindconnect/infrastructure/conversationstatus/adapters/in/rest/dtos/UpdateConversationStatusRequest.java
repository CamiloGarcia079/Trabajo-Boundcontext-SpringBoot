package com.mindconnect.infrastructure.conversationstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de conversations_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateConversationStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
