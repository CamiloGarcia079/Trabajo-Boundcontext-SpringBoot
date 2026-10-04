package com.mindconnect.domain.risklevel.event;

import java.time.LocalDateTime;

import com.mindconnect.domain.common.event.DomainEvent;
import com.mindconnect.domain.risklevel.model.valueobject.RiskLevelId;

/**
 * Evento de dominio: se actualizó un registro de risk_levels.
 */
public record RiskLevelUpdatedEvent(
        RiskLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
