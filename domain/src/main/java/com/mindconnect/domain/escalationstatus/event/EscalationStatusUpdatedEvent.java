package com.mindconnect.domain.escalationstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Evento de dominio: se actualizó un registro de escalations_statuses.
 */
public record EscalationStatusUpdatedEvent(
        EscalationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
