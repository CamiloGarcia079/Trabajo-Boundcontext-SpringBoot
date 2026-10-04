package com.mindconnect.domain.chatconversationaisetting.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

/**
 * Evento de dominio: se actualizó un registro de chat_conversation_ai_settings.
 */
public record ChatConversationAiSettingUpdatedEvent(
        ChatConversationAiSettingId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
