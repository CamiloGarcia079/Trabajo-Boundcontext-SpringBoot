package com.mindconnect.domain.airunstatus.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.airunstatus.model.valueobject.AiRunStatusId;

/**
 * Evento de dominio: se actualizó un registro de ai_runs_statuses.
 */
public record AiRunStatusUpdatedEvent(
        AiRunStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
