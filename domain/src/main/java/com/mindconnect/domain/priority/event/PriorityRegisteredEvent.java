package com.mindconnect.domain.priority.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.priority.model.valueobject.PriorityId;

/**
 * Evento de dominio: se registró un registro de priorities.
 */
public record PriorityRegisteredEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
