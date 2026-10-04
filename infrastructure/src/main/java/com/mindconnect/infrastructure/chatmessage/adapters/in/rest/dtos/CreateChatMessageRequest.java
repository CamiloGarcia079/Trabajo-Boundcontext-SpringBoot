package com.mindconnect.infrastructure.chatmessage.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_messages.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatMessageRequest(
        @NotNull UUID conversationId,
        @NotNull UUID messageTypeId,
        @NotNull UUID participantId,
        @NotBlank String content,
        @NotBlank String metadata
) {
}
