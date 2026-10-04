package com.mindconnect.infrastructure.chatconversation.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_conversations.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatConversationRequest(
        @NotNull UUID conversationStatusId,
        @NotNull UUID priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy
) {
}
