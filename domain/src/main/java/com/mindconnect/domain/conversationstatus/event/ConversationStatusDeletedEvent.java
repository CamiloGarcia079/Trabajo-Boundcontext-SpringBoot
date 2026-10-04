package com.mindconnect.domain.conversationstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.conversationstatus.model.valueobject.ConversationStatusId;

/**
 * Evento de dominio: se eliminó un registro de conversations_statuses.
 */
public record ConversationStatusDeletedEvent(
        ConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
