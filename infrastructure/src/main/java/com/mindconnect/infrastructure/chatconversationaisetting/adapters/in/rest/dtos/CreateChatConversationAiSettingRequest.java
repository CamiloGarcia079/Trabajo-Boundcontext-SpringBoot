package com.mindconnect.infrastructure.chatconversationaisetting.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

/**
 * Cuerpo JSON que recibe la API para crear un registro de chat_conversation_ai_settings.
 * Las anotaciones validan lo que llega antes de llamar al caso de uso.
 */
public record CreateChatConversationAiSettingRequest(
        @NotNull UUID conversationId,
        @NotNull Boolean aiEnabled,
        @NotNull UUID defaultModelId
) {
}
