package com.mindconnect.infrastructure.chatescalation.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_escalations.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatEscalationRequest(
        @NotNull UUID conversationId,
        @NotNull UUID statusId,
        @NotNull Boolean fromAi,
        @NotBlank String reason
) {
}
