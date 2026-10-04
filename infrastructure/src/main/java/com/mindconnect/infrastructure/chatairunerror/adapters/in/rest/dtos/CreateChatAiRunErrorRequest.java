package com.mindconnect.infrastructure.chatairunerror.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_ai_run_errors.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatAiRunErrorRequest(
        @NotNull UUID aiRunId,
        @NotBlank String errorMessage,
        @NotBlank @Size(max = 80) String errorCode,
        @NotBlank @Size(max = 120) String providerErrorId
) {
}
