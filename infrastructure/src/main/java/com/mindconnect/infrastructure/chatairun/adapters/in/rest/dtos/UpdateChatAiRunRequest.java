package com.mindconnect.infrastructure.chatairun.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para actualizar un registro de chat_ai_runs.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record UpdateChatAiRunRequest(
        @NotNull UUID conversationId,
        @NotNull UUID messageId,
        @NotNull UUID modelId,
        @NotNull UUID aiRunStatusId
) {
}
