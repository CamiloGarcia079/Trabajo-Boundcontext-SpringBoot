package com.mindconnect.infrastructure.airunstatus.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de ai_runs_statuses.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateAiRunStatusRequest(
        @NotBlank @Size(max = 50) String nameStatus
) {
}
