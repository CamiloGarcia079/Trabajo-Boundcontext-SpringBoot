package com.mindconnect.domain.chatescalation.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.chatescalation.model.valueobject.ChatEscalationId;

/**
 * Evento de dominio: se registró un registro de chat_escalations.
 */
public record ChatEscalationRegisteredEvent(
        ChatEscalationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
