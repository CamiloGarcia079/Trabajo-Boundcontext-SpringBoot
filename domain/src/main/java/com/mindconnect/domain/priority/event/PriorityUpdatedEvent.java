package com.mindconnect.domain.priority.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

/**
 * Evento de dominio: se actualizó un registro de priorities.
 */
public record PriorityUpdatedEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
