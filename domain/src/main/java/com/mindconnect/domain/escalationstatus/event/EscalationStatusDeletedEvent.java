package com.mindconnect.domain.escalationstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Evento de dominio: se eliminó un registro de escalations_statuses.
 */
public record EscalationStatusDeletedEvent(
        EscalationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
