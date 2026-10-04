package com.mindconnect.domain.chatconversation.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatconversation.model.valueobject.ChatConversationId;

/**
 * Evento de dominio: se registró un registro de chat_conversations.
 */
public record ChatConversationRegisteredEvent(
        ChatConversationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
