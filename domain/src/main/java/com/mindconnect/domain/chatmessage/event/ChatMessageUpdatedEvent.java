package com.mindconnect.domain.chatmessage.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatmessage.model.valueobject.ChatMessageId;

/**
 * Evento de dominio: se actualizó un registro de chat_messages.
 */
public record ChatMessageUpdatedEvent(
        ChatMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
